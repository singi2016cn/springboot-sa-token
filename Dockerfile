FROM bellsoft/liberica-runtime-container:jre-17-cds-slim-glibc
WORKDIR /app
COPY target/*.ja app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]