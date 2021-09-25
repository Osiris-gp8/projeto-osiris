FROM nginx

COPY ./docker/nginx/frontend-nginx.conf /etc/nginx/nginx.conf

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]