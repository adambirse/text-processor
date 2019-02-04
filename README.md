# Text Processor


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


### Processing

Intended to process data received from this service

https://github.com/adambirse/text-ingester


