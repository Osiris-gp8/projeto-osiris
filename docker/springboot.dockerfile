#Imagem base
FROM maven:3.8.2-jdk-8

#Criador do Dockerfile
LABEL maintainer="Kaio Baleeiro"

#Copiando o projeto pra dentro do container
COPY ./osiris-api/ /var/www/

#Diretório de trabalho (onde ele roda comandos como mvnw clean install)
WORKDIR /var/www/

#Criando o pacote da aplicação
RUN mvn clean package

#Comando de entrada para rodar a aplicação
ENTRYPOINT java -jar ./target/osiris-api-0.0.1-SNAPSHOT.jar

#Expondo a porta que a aplicação roda
EXPOSE 8080
