FROM mysql

COPY ./script-banco-de-processamento.sql ./docker-entrypoint-initdb.d

ENV MYSQL_ROOT_PASSWORD = "bandtec"
ENV MYSQL_DATABASE = "processamento_db"

EXPOSE 3306