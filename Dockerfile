FROM openjdk:8-jre-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/svc.jar"]
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/svc.jar
