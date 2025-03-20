# Usa una imagen base oficial de OpenJDK
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR del proyecto en el contenedor en /app
COPY target/api-spring-angular-0.0.1-SNAPSHOT.jar /app/api-spring-angular.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Ejecuta el archivo JAR
ENTRYPOINT ["java", "-jar", "api-spring-angular.jar"]

# docker build -t backend:2.0 .
# docker image list
# docker tag backend:2.0 antoniorodenas/backend:2.0
# docker login
# hub.docker.com
# docker push antoniorodenas/backend:2.0
