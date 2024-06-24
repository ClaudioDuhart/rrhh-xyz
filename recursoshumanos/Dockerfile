FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/recursoshumanos-0.0.1.jar
COPY ${JAR_FILE} app.rrhh.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.rrhh.jar"]