#!/bin/bash

# install docker and docker-compose
wget -qO- https://get.docker.com/ | sh
apt-get -y install python-pip
pip install docker-compose

# install barorder
curl -sL -o docker-compose.yml https://raw.githubusercontent.com/therickys93/barorder/master/docker-compose.production.yml
docker-compose build
docker-compose up -d
docker-compose logs -f