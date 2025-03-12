# Usar la imagen base de Temurin JDK 21
FROM eclipse-temurin:21-jdk

# Establecer el autor de la imagen
LABEL authors="AntonioRodenasJurado"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# docker build -t backend:2.0 .
# docker image list
# docker tag backend:2.0 antoniorodenas/backend:2.0
# docker login
# hub.docker.com
# docker push antoniorodenas/backend:2.0
