# Docker Nim

Simple Docker image including the Nim compiler https://nim-lang.org/docs/nimc.html

# Build image

```
$ docker build -t nim-dev .
```

Note that as of 12/6/2017, `nim` and `nimble` were still in the Alpine testing repository, thus requiring the following line prior to execute `apk add`:

```
RUN echo http://nl.alpinelinux.org/alpine/edge/testing >> /etc/apk/repositories
```

# Usage

* Compile a source file located on the host file system (`hello.nim`), and execute:

```
$ docker run --rm -v `pwd`/src/:/src/ nim-dev nim c -r /src/hello.nim

```

* Compile a source file located on the host file system (`hello.nim`) in release mode:

```
$ docker run --rm -v `pwd`/src/:/src/ nim-dev nim c -d:release /src/hello.nim

```

* Execute the binary compile in the previous step:

```
$ docker run --rm -v `pwd`/src/:/src/ nim-dev /src/hello

```
