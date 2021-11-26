#!/usr/bin/env bash

if [ -z $1 ]
then   
    echo 'URL não informada'
    exit 1
fi

endereco=$1
path_json_temp=./temp/arquivo.json

instance_id=$(curl -s http://169.254.169.254/latest/meta-data/instance-id)

curl $1 -o $path_json_temp

contagem=$(cat ./temp/arquivo.json | awk -F: '{ print $2 }' | awk -F} '{ print $1 }')

rm $path_json_temp

/usr/bin/env aws cloudwatch put-metric-data --metric-name ArquivosComFalhas --namespace Custom --unit Count --value $contagem --dimensions InstanceId=$instance_id