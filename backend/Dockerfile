# Usar la imagen base de Temurin JDK 21
FROM eclipse-temurin:21-jdk

# Establecer el autor de la imagen
LABEL authors="AntonioRodenasJurado"

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/api-spring-angular.jar /app/app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
# docker build -t api-spring-angular-backend .
# docker images list

