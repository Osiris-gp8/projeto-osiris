#Imagem base
FROM openjdk:8-jdk-alpine

#Criador do Dockerfile
LABEL maintainer="Kaio Baleeiro"

#Copiando o projeto pra dentro do container
COPY /osiris-api /var/www

#Diretório de trabalho (onde ele roda comandos como mvnw clean install)
WORKDIR /var/www

#Instalar dependências e gerar o jar
RUN mvnw clean install
RUN mvnw clean package

#Comando de entrada para rodar a aplicação
ENTRYPOINT java -jar target/forum-0.0.1-SNAPSHOT.jar

#Expondo a porta que a aplicação roda
EXPOSE 8080
