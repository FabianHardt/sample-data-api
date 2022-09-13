#!/bin/bash

IMAGENAME_API=sample-data-api:1.0
docker build . -t $IMAGENAME_API

cd Postgres
IMAGENAME_PG=pg-adventureworks:1.0
docker build . -t $IMAGENAME_PG

cd ..
docker-compose up
