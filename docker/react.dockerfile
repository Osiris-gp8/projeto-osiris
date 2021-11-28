ARG OSIRIS_BACK_URL

FROM node:14-slim

ENV OSIRIS_BACK_URL=$OSIRIS_BACK_URL

COPY ./front-end-osiris/ /var/www

WORKDIR /var/www

RUN npm install
ENTRYPOINT npm start

EXPOSE 3000