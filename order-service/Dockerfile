FROM openjdk:14-alpine
COPY build/libs/order-service-*-all.jar order-service.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "order-service.jar"]