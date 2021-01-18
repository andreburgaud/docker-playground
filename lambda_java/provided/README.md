# README

Minimum projects demonstrating a containerized Lambda in Java, using the Java 11
AWS provided image.

## Build Docker Image

```
$ docker build -t lambdajava11 .
```

* You can use the local Makefile to build all the images: `make build`


## Local Testing

### Start Lambda

The RIE is integrated in the AWS provided Java 11, so you can start the lambda locally as follows:

```
$ docker run --rm -p 9000:8080 lambdajava11
```

### Send Request

```
$ curl -v POST "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{"body": "{ \"name\": \"jon\", \"age\": 10 }"}'
{"statusCode":200,"headers":null,"multiValueHeaders":null,"body":"{\"message\":\"jon is 10 years old!\"}"}
```
