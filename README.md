# Text Ingester


## Building

`./gradlew build`


`cd docker`

`./prepare_build_docker.sh`

`cd build_context`
`docker build -t text-processor .`


## Running

Follow the instructions here to start a kafka cluster and to create the `text` topic

https://github.com/adambirse/kafka-docker-compose

`cd ../..`

`docker-compose up -d`


### Send some data

`curl -d '{"text":"value"}' -H "Content-Type: application/json" -X POST http://localhost:8080/text`