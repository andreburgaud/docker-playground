# Docker Playground

Experimentation with Docker images and containers.

# Some Useful Docker Commands

```
$ docker build -t <image_name> .                                   # Build image
$ docker run --rm -it <image_name>                                 # Start container (interactive)
$ docker run --rm -it <image_name> sh                              # Start container with shell (interactive)
$ docker run --rm -p 8080:80 <image_name>                          # Start container binding port 8080 to container port 80
$ docker run --rm --name <container_name> -p 8080:80 <image_name>  # Start container with explicit name
$ docker run --rm -d -p 8080:80 <image_name>                       # Start container as daemon
$ docker exec -it <container_name> sh                              # Access a running container
$ docker stop <container_name>                                     # Stop container (STOPSIGNAL SIGTERM)
$ docker kill <container_name>                                     # Kill container
$ docker ps                                                        # List running active container
$ docker ps -a                                                     # List active container
$ docker rm $(docker ps -a -f status=exited -f status=created -q)  # Delete all containers
$ docker rmi $(docker images -f dangling=true -q)                  # Remove dangling images
```

# Docker Images

## Lua

```
$ docker build -t lua-dev .
$ docker run --rm lua-dev
```

* Note: No readline in REPL

## LuaJIT

```
$ docker build -t luajit-dev .
$ docker run --rm luajit-dev
```

* No readline in REPL
