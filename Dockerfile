# Usar uma imagem base com Java 17
FROM openjdk:17-jdk-slim

# Criar diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado para o contêiner
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
