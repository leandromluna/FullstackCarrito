# Usa una imagen base de Maven con OpenJDK
FROM openjdk:17-jdk-alpine AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR a la imagen
COPY target/carrito-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
