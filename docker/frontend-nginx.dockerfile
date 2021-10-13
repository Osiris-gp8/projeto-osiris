FROM nginx

COPY ./docker/nginx/frontend-nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 85

# CMD ["nginx", "-g", "daemon off;"]
