FROM openjdk:8
ADD /build/libs/delivery-mercado-26-11-19.jar delivery-mercado-26-11-19.jar
ENTRYPOINT ["java", "-jar", "delivery-mercado-26-11-19.jar", "--app.db.host=db"]
EXPOSE 9090
