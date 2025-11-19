#FROM openjdk:17-jdk-slim
FROM amazoncorretto:17
COPY ./target/devops.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devops.jar","1"]
#ENTRYPOINT ["java", "-jar", "reportHub-0.1.0.4-jar-with-dependencies.jar","1"]
#CMD ["tail", "-f", "/dev/null"]#ENTRYPOINT ["java", "-jar", "reportHub-0.1.0.4-jar-with-dependencies.jar","1"]
#CMD ["tail", "-f", "/dev/null"]