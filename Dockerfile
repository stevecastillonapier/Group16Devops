FROM openjdk:latest
COPY ./target/reportHub-0.1.0.0-jar-with-dependencies.jar /tmp
WORKDIR /tmp
#ENTRYPOINT ["java", "-jar", "reportHub-0.1.0.0-jar-with-dependencies.jar"]
 CMD ["tail", "-f", "/dev/null"]