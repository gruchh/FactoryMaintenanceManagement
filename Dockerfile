# Użyj obrazu bazowego OpenJDK 17
FROM openjdk:17-jdk-alpine

# Ustawienie zmiennej środowiskowej dla katalogu aplikacji
ENV APP_HOME=/app

# Utworzenie katalogu aplikacji
RUN mkdir -p $APP_HOME

# Ustawienie katalogu roboczego
WORKDIR $APP_HOME

# Kopiowanie pliku JAR do obrazu
COPY ./target/FactoryManagement-0.0.1-SNAPSHOT.jar /app.jar

# Ustawienie punktu wejścia
ENTRYPOINT ["java", "-jar", "/app.jar"]