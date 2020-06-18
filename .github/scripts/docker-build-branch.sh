#!/bin/sh

echo "$GITHUB_TOKEN" | docker login docker.pkg.github.com -u adityastic --password-stdin
cp deployment/application.properties src/main/resources/
./mvnw clean install -DskipTests
docker-compose build
docker tag newspaper-delivery-system:latest docker.pkg.github.com/adityastic/newspaperdeliverysystem:$BRANCH_NAME
docker push docker.pkg.github.com/adityastic/newspaperdeliverysystem:$BRANCH_NAME