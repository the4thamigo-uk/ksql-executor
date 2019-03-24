#!/bin/bash
script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

source $script_dir/env.sh

docker run --env-file <(cat << EOF
APP_KSQL_SCHEMA_REGISTRY_URL=http://schema-registry:8081
APP_BOOTSTRAP_SERVERS=broker:9092
APP_AUTO_OFFSET_RESET=earliest
EOF
) -ti --network ksql-executor_default ksql-executor "create stream x with(kafka_topic='x', value_format='avro'); create stream y with(kafka_topic='y') as select * from x;"

