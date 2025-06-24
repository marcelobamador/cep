# Use a imagem oficial do OpenJDK 17 baseada em Alpine para menor tamanho
FROM eclipse-temurin:17-jre-alpine

# Define diretório de trabalho
WORKDIR /app

# Copia o jar gerado para o container
COPY target/cep-1.0.0.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]