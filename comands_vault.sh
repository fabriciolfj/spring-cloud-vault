kubectl exec -it vault-0 -n vault -- /bin/sh

#habilitando vault no kubernetes
vault auth enable kubernetes

#criando meio de autenticacao
vault write auth/kubernetes/config \
    kubernetes_host="https://$KUBERNETES_PORT_443_TCP_ADDR:443" \
    token_reviewer_jwt="$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)" \
    kubernetes_ca_cert=@/var/run/secrets/kubernetes.io/serviceaccount/ca.crt \
    issuer="https://kubernetes.default.svc.cluster.local"

#criando um policy
vault policy write internal-app - <<EOF
path "database/creds/default" {
  capabilities = ["read"]
}
EOF

#criando um serviceaccount, onde nos o vincularemos a policy e ao namesapce da app
kubectl create sa internal-app

#criando função no vault
vault write auth/kubernetes/role/internal-app \
    bound_service_account_names=internal-app \
    bound_service_account_namespaces=default \
    policies=internal-app \
    ttl=24h

#habilitar o macanismo de banco de dados no vault
vault secrets enable database

#configurando o plugin do postgres e a configuração de conexao com a base
vault write database/config/postgres \
    plugin_name=postgresql-database-plugin \
    allowed_roles="default" \
    connection_url="postgresql://{{username}}:{{password}}@postgres.default:5432?sslmode=disable" \
    username="postgres" \
    password="admin123"

#criando uma role, onde dará a conceção de 10min para enviar as credenciais do banco a app, para utilizar na conexao com o postgres
vault write database/roles/default db_name=postgres \
    creation_statements="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}';GRANT SELECT, UPDATE, INSERT ON ALL TABLES IN SCHEMA public TO \"{{name}}\";GRANT USAGE,  SELECT ON ALL SEQUENCES IN SCHEMA public TO \"{{name}}\";" \
    default_ttl="1m" \
    max_ttl="10m"

#pegando as credencias para se conectar na dash do vault
vault read database/creds/default

#ver quandos usuarios a app esta gerando
kubectl exec svc/postgres -i -t -- psql -U postgres
\du