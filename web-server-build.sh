docker build -f ./docker/frontend-nginx.dockerfile -t osiris/front-loadbalance .
docker build -f ./docker/react.dockerfile -t osiris/frontend .