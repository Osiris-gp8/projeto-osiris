docker build --no-cache -f ./docker/frontend-nginx.dockerfile -t osiris/front-loadbalance .
docker build --no-cache -f ./docker/react.dockerfile -t osiris/frontend .