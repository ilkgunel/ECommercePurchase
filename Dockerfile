FROM openjdk:18
COPY target/ECommercePurchase-1.0-SNAPSHOT.jar ECommercePurchase-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ECommercePurchase-1.0-SNAPSHOT.jar"]