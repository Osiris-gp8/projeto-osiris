#!/usr/bin/env bash

if [ -z $1 ]
then   
    echo 'URL não informada'
    exit 1
fi

endereco=$1

instance_id=$(curl -s http://169.254.169.254/latest/meta-data/instance-id)

curl $1 -o ./temp/arquivo.json

contagem=$(cat ./temp/arquivo.json | awk -F: '{ print $2 }' | awk -F} '{ print $1 }')

/usr/bin/env aws cloudwatch put-metric-data --metric-name ArquivosComFalhas --namespace Custom --unit Count --value $contagem --dimensions InstanceId=$instance_id