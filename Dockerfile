FROM openjdk:17
RUN mkdir -p /app/
EXPOSE 8080
ADD build/libs/ForeignExchange-0.0.1-SNAPSHOT.jar /app/ForeignExchange-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/ForeignExchange-0.0.1-SNAPSHOT.jar"]