# Etapa 1: Build da aplicação usando Maven
FROM maven:3.9.6-eclipse-temurin-22-jammy AS build

COPY pom.xml .
COPY src ./src
# Adicione o agente New Relic ao diretório do contêiner
COPY /newrelic-java/newrelic /app/newrelic

RUN mvn clean install -DskipTests

# Verifique se o newrelic.jar foi copiado corretamente
RUN ls -la /app/newrelic

# Etapa 2: Criação da imagem final com o JDK
FROM openjdk:22-jdk-slim

# Copie o JAR gerado na etapa de build para a imagem final
COPY --from=build /target/*.jar /app/fastfood.jar

# Inicie a aplicação com o agente do New Relic
CMD ["java", "-javaagent:/app/newrelic/newrelic.jar", "-Dnewrelic.config.file=/app/newrelic/newrelic.yml", "-jar", "/app/fastfood.jar"]


