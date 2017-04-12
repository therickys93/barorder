#!/bin/bash

curl -sL -o docker-compose.yml https://raw.githubusercontent.com/therickys93/barorder/master/docker-compose.production.yml
docker-compose build
docker-compose up -d
docker-compose logs -f