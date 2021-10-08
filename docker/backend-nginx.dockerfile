FROM nginx

COPY ./docker/nginx/backend-nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 90 443 80

CMD ["nginx", "-g", "daemon off;"]