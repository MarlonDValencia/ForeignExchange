FROM openjdk:21
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/ForeignExchange-0.0.1-SNAPSHOT.jar /app/ForeignExchange-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/ForeignExchange-0.0.1-SNAPSHOT.jar"]