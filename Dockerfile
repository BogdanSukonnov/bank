FROM openjdk:13-jdk-alpine
COPY build/libs/bank-0.0.1-SNAPSHOT.jar bank.jar
ENTRYPOINT ["java","-jar","/bank.jar"]