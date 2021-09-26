FROM node:14-slim as builder

COPY ./front-end-osiris/ /var/www

WORKDIR /var/www

RUN npm install
ENTRYPOINT npm start
RUN npm build
EXPOSE 3000


FROM nginx

# RUN rm /etc/nginx/conf.d/default.conf

COPY --from=builder /var/www/build /usr/share/nginx/html
COPY ./docker/nginx/frontend-nginx.conf /etc/nginx/conf.d/loadbalance.conf

EXPOSE 80

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]