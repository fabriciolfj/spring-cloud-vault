helm repo add hashicorp https://helm.releases.hashicorp.com

helm repo update

kubectl create ns vault

helm install vault hashicorp/vault \
    --set "server.dev.enabled=true" \
    -n vault