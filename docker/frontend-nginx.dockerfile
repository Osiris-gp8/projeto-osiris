FROM nginx

# RUN rm /etc/nginx/conf.d/default.conf

COPY ./docker/nginx/frontend-nginx.conf /etc/nginx/conf.d/nginx.conf

EXPOSE 80

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]
