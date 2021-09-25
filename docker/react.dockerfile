FROM node:14-slim

COPY ./front-end-osiris/ /var/www

WORKDIR /var/www

RUN npm install
ENTRYPOINT npm start

EXPOSE 3000
