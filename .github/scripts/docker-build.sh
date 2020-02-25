#!/bin/sh

echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
cp deployment/application.properties src/main/resources/
./mvnw clean install -DskipTests
docker-compose build
docker tag newspaper-delivery-system:latest "$DOCKER_USERNAME"/newspaper-delivery-system:latest
docker tag newspaper-delivery-system:latest "$DOCKER_USERNAME"/newspaper-delivery-system:"$REL_VER"
docker push "$DOCKER_USERNAME"/newspaper-delivery-system:latest
docker push "$DOCKER_USERNAME"/newspaper-delivery-system:"$REL_VER"
docker images