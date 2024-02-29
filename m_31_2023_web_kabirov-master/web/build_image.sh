#!/bin/sh
echo "pulling from git"
git pull origin master

echo "building jar file"
mvn clean package

echo "building docker image"
sudo docker build -t web-app:latest .