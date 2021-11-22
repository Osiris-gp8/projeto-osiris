FROM node:14-slim AS build

COPY ./front-end-osiris /var/www

WORKDIR /var/www

RUN npm install
RUN npm run build


FROM nginx

COPY --from=build /var/www/ /usr/share/nginx/html

COPY ./docker/nginx/server-nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 85

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]