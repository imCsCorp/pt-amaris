# Etapa 1: Construcción del artefacto
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia solo los archivos necesarios para el build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/prueba-tecnica-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
