# Build stage
FROM azul/zulu-openjdk-alpine:15 as build

RUN apk update && apk add --no-cache maven

WORKDIR /src

# cache and copy dependencies
ADD pom.xml .
RUN mvn dependency:go-offline dependency:copy-dependencies

# compile the function
ADD . .
RUN mvn package

# Runtime stage
FROM azul/zulu-openjdk-alpine:15-jre

# Integrate the RIE (Runtime Interface Emulator) into the image
RUN wget -O /usr/local/bin/aws-lambda-rie https://github.com/aws/aws-lambda-runtime-interface-emulator/releases/latest/download/aws-lambda-rie
RUN chmod +x /usr/local/bin/aws-lambda-rie

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

WORKDIR /function

COPY bootstrap .
RUN chmod +x bootstrap

COPY --from=build /src/target/dependency/*.jar ./
COPY --from=build /src/target/*.jar ./

ENTRYPOINT ["entrypoint.sh"]
CMD [ "example.App::sayHello" ]

# To Debug
# ENTRYPOINT ["sh"]
