FROM alpine:edge

RUN echo http://nl.alpinelinux.org/alpine/edge/testing >> /etc/apk/repositories && \
    apk add --no-cache musl musl-dev gcc nim nimble

CMD ["sh"]
