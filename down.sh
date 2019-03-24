#!/bin/bash
script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

source $script_dir/env.sh

echo $dc
$dc down

if [ "$1" == "purge" ]; then
  echo delete $script_dir/.kafka_\* ?
  sudo rm -rf $script_dir/.kafka_*
fi
