# KSQL Executor

_Run KSQL by passing it into a generic docker container_ without needing a KSQL server or KSQL CLI

## Getting Started

Build

```
mvn clean package
```

Start the Kafka broker (run `./down.sh purge` to purge any previous kafka broker state)

```
./up.sh
```

...wait and try to send data to topic 'x'

```
./create_topics.sh
```

Start a KSQL executor container to send the data from topic 'x' to topic 'y'

```
./launch.sh
```

Verify that some data was forwarded to topic 'z'

```
./consume.sh y
```
