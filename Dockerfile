FROM openjdk:17-jdk-slim
COPY ./target/reportHub-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
#ENTRYPOINT ["java", "-jar", "reportHub-0.1.0.3-jar-with-dependencies.jar","1"]
CMD ["tail", "-f", "/dev/null"]