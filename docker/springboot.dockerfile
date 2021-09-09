#Imagem base
FROM maven

#Criador do Dockerfile
LABEL maintainer="Kaio Baleeiro"

#Copiando o projeto pra dentro do container
COPY ./osiris-api /var/www

#Diretório de trabalho (onde ele roda comandos como mvnw clean install)
WORKDIR /var/www

#Build da aplicação
RUN mvn clean install

#Gerando JAR
RUN mvn package

#Comando de entrada para rodar a aplicação
ENTRYPOINT java -jar osiris-api-0.0.1-SNAPSHOT.jar

#Expondo a porta que a aplicação roda
EXPOSE 8080