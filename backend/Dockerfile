# Usar la imagen base de Temurin JDK 21
FROM eclipse-temurin:21-jdk

# Establecer el autor de la imagen
LABEL authors="AntonioRodenasJurado"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# docker build -t backend:1.0 .
# docker image list
# docker tag backend:1.0 anrodenasj/backend:1.0
# docker login
# hub.docker.com
# docker push anrodenasj/backend:1.0

