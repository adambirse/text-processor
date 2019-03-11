#!/usr/bin/env bash

set -e
echo "building ......"
./gradlew clean build -x test
cd docker
./prepare_build_docker.sh
cd build_context
docker build -t text-processor .
cd ../..
echo "deploying"
docker-compose up -d
echo "Deployed ......"
