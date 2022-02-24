eval $(minikube docker-env)
mvn clean install

kubectl delete secrets mysql-server-credentials
kubectl delete configmap config-repo-product
kubectl delete service mysql
kubectl delete deployment mysql
kubectl delete service products
kubectl delete deployment products

kubectl create secret generic mysql-server-credentials \
    --from-literal=MYSQL_ROOT_PASSWORD=root \
    --from-literal=MYSQL_DATABASE=product \
    --save-config;

#kubectl create configmap config-repo-product --from-file=config-repo/product.yml --save-config
kubectl apply -f kubernetes/mysql.yaml

#docker build -t fabricio211/product-service .
kubectl apply -f kubernetes/product.yml

microk8s.enable ingress

microk8s enable dns

kubectl apply -f kubernetes/ingress.yml