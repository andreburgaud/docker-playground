# Example of a Custom Lambda Docker Image for Java

This is a working example on how to create containerized AWS lambda image. This is an extension of the AWS example provided at https://github.com/aws/aws-lambda-java-libs/tree/master/aws-lambda-java-runtime-interface-client.

I had to modify the AWS example to get it running.

The original [AWS project](https://github.com/aws/aws-lambda-java-libs/tree/master/aws-lambda-java-runtime-interface-client) is released under Apache-2.0 License. This modified project is released under the [MIT license](../../LICENSE.md) like the other Docker projects in this repo.

## Differences

As I could build but not run the original project as it was described in the [README](https://github.com/aws/aws-lambda-java-libs/blob/master/aws-lambda-java-runtime-interface-client/README.md), I slightly modified the generation of the image by:

1. Integrating the Runtime Interface Emulator (RIE) in the image to help with local testing
1. Added an entrypoint.sh and bootsrap files to control the execution of the lambda

## Build

```
$ docker build -t java-ric .
```

## Run

```
$ docker run --rm -p 9000:8080 java-ric
```

## Test

After locally starting the container, test it with curl:

```
$ curl "http://localhost:9000/2015-03-31/functions/function/invocations" -d '{}'
"Hello λ!"
```

## Makefile

You can use make to execute the build, run, test and clean. For more information, run:

```
$ make
```

## License


[MIT License](../../LICENSE.md)