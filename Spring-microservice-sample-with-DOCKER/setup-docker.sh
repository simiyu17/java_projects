#!/bin/bash

#Package the services
mvn clean install

#Stop docker container if they are up
docker-compose down

#Build images using docker compose
docker-compose build

#Start image instances (containers)
docker-compose up