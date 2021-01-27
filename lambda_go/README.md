# README

Minimum projects for containerized Lambdas in Go, using three different base images:

1. An AWS provided docker image
1. A custom Alpine docker image
1. A scratch Docker image

## Build Docker Image

You can build each image by running the following command in subdirectories containing
a Docker file:

```
$ DOCKER_BUILDKIT=1 docker build -t lambdago .
```

* If you want to keep a docker image for each project, use a different name for each
image
* You can use the local Makefile to build all the images: `make build`


## Install AWS Lambda Runtime Interface Emulator (RIE)

Because the RIE is not integrated in the images, you need to install it on the host
machine to perform local tests:

```
mkdir -p ~/.aws-lambda-rie && curl -Lo ~/.aws-lambda-rie/aws-lambda-rie \
https://github.com/aws/aws-lambda-runtime-interface-emulator/releases/latest/download/aws-lambda-rie \
&& chmod +x ~/.aws-lambda-rie/aws-lambda-rie
```

Note: The RIE needs to be installed only once.

## Local Testing

### Start Lambda

You need to start the lambda using the RIE installed on the host:

```
$ docker run --rm  -v ~/.aws-lambda-rie:/aws-lambda -p 9000:8080 --entrypoint /aws-lambda/aws-lambda-rie lambdago /main
```

### Send Request

```
$ curl -v POST "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{"body": "{ \"name\": \"jon\", \"age\": 10 }"}'
{"statusCode":200,"headers":null,"multiValueHeaders":null,"body":"{\"message\":\"jon is 10 years old!\"}"}
```
