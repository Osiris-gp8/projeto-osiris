FROM node:14-slim AS build

COPY ./front-end-osiris /var/www/html

WORKDIR /var/www/html

RUN npm install
RUN npm install --save-dev jest
RUN npm run build


FROM nginx

COPY --from=build /var/www/html/build /usr/share/nginx/html

COPY ./docker/nginx/server-nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 85
EXPOSE 90

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]