docker build -f ./docker/frontend-nginx.dockerfile -t osiris/front-loadbalance .
docker build -f ./docker/backend-nginx.dockerfile -t osiris/back-loadbalance .
docker build -f ./docker/react.dockerfile -t osiris/frontend .
docker build -f ./docker/springboot.dockerfile -t osiris/backend .