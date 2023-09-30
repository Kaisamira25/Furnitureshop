# Chose base image
FROM openjdk:17-alpine
# authur
LABEL authors="Kaisamirashe"
#create dir
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
#copy .jar file into /opt/app.jar
COPY target/*.jar /opt/app.jar
# Run cmd
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar