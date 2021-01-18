# Build stage
FROM golang:1.15.6-alpine3.12 as build

RUN apk update && apk add --no-cache git
RUN go env -w GOPROXY=direct

ADD . .

RUN go get -d -v
RUN GOOS=linux GOARCH=amd64 CGO_ENABLED=0 go build -ldflags="-w -s" -o /main

# copy artifacts to a clean image
FROM public.ecr.aws/lambda/provided:al2
COPY --from=build /main /main

ENTRYPOINT [ "/main" ]
