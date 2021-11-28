#!/usr/bin/env bash

if [ -z $1 ]
then
    echo 'URL não informada'
    exit 1
fi

endereco=$1

instance_id=$(curl -s http://169.254.169.254/latest/meta-data/instance-id)

response=$(curl -s $1)

tamanho=$( echo $response | awk -F: '{ print $2 }' | awk -F} '{ print $1 }')

/usr/bin/env aws cloudwatch put-metric-data --metric-name TamanhoArquivosEmBytes --namespace Custom --unit Count --value $tamanho --dimensions InstanceId=$instance_id