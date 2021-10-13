FROM nginx

COPY ./docker/nginx/backend-nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 90

# ENTRYPOINT [ "nginx" ]
# CMD ["-g", "daemon off;"]