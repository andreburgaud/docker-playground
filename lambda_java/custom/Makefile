.DEFAULT_GOAL := help

.PHONY: build
build:
	DOCKER_BUILDKIT=1 docker build -t java-ric .

run:
	docker run --rm -p 9000:8080 java-ric

test:
	curl "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{}'

clean:
	docker rmi java-ric:latest || true
	docker rmi $(docker images -f dangling=true -q) || true
	docker rm $(docker ps -a -f status=exited -q) || true

help:
	@echo 'Makefile for lambda-ric (Containerized Java Lambda)'
	@echo
	@echo 'Usage:'
	@echo '    make build       Create the Docker image'
	@echo '    make run         Start the container locally'
	@echo '    make test        Send request to the lambda running locally'
