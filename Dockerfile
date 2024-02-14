FROM amazoncorretto:17-alpine3.19-jdk

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]