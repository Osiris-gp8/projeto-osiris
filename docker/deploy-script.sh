echo "$PRIVATE_KEY" > private_key && chmod 600 private_key

scp -o StrictHostKeyChecking=no ./docker/docker-compose.yml -i private_key ${USER}@${HOSTNAME}:/home/ubuntu/docker-compose.yml

ssh -o StrictHostKeyChecking=no -i private_key ${USER}@${HOSTNAME} '
    docker-compose up -d --scale springboot=2
'