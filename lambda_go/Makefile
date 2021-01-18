build:
	docker build -t lambdagocust ./custom
	docker build -t lambdagoprov ./provided
	docker build -t lambdagoscra ./scratch

clean:
	docker rmi lambdagocust lambdagoprov lambdagoscra
	docker rmi $(docker images -f dangling=true -q) || true
