FROM mysql

ENV MYSQL_ROOT_PASSWORD=bandtec
ENV MYSQL_DATABASE=osiris

COPY ./mount-database.sql ./docker-entrypoint-initdb.d

EXPOSE 3306