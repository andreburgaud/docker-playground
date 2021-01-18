# Build layer
FROM public.ecr.aws/lambda/java:11 as build

ENV JAVA_HOME=/var/lang

COPY src/ src/
COPY gradle/ gradle/
COPY build.gradle gradlew settings.gradle ./
RUN ./gradlew build

# Runtime layer (final container)
FROM public.ecr.aws/lambda/java:11

COPY --from=build /var/task/build/classes/java/main /var/task/
COPY --from=build /var/task/build/dependency/* /var/task/lib/

CMD ["example.App::handleRequest"]
