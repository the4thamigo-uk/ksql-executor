# KSQL Executor

_Run KSQL by passing it into a generic docker container_ without needing a KSQL server or KSQL CLI

## Getting Started

Build

```
mvn package
```

Start the Kafka broker (run `sudo rm -rf .kafka_data` to purge any previous kafka broker state)

```
docker-compose up -d
```

...wait and try to send data to topic 'x'

```
cat ./data/x.dat | ./produce.sh x
```

Start a KSQL executor container to send the data from topic 'x' to topic 'z'

```
docker run -ti --network ksql-docker_default ksql-executor "create stream x with(kafka_topic='x', value_format='avro'); create stream y with(kafka_topic='z') as select * from x;"
```

Verify that some data was forwarded to topic 'z'

```
./kafkacat.sh -C -o 0 -t z
```

TODO:

- Improve configurability
- Read script from stdin or command line args

