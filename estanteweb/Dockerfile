# Etapa 1: Build
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copia arquivos de configuração e dependências
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copia o código-fonte e gera o pacote
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Execução com usuário não-root por segurança
RUN useradd -m appuser && chown -R appuser /app
USER appuser

# Copia o artefato compilado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
