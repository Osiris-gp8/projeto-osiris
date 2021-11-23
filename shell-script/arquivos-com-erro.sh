#!/bin/bash

if [ -z $1 ]
then   
    echo 'URL não informada'
    exit 1
fi

endereco=$1

instance_id=$(curl -s http://169.254.169.254/latest/meta-data/instance-id)

curl $1 -o ./temp/arquivo.json

echo $(cat ./temp/arquivo.txt)