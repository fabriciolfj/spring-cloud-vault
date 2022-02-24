eval $(minikube docker-env);
mvn clean install spring-boot:build-image
docker push fabricio211/product:1.101.0
eval $(minikube docker-env -u);


cd ./kubernetes/product
kubectl apply -f ./ -R
cd ..
cd ..