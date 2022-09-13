#!/bin/bash

IMAGENAME=pg-adventureworks:1.0

docker build . -t $IMAGENAME
docker run --net bridge --name postgres -d -e POSTGRES_PASSWORD=postgres -p5432:5432 $IMAGENAME
