FROM openjdk:11

COPY target/sms-billing-system-0.0.1-SNAPSHOT.jar sms-billing-system-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/sms-billing-system-0.0.1-SNAPSHOT.jar"]