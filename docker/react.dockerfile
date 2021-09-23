FROM node:14-slim

LABEL maintainer="Kaio Baleeiro"

COPY ./front-end-osiris/ /var/www

WORKDIR /var/www

RUN npm install
ENTRYPOINT npm start

EXPOSE 3000