FROM openjdk:8-jre-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/svc.jar"]
ARG JAR_FILE
ADD ${JAR_FILE} /svc.jar
ADD /ext /ext
