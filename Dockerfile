FROM openjdk:17-jdk-slim
COPY ./target/Drip-Chip-System-0.0.1-SNAPSHOT.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/animal-chipization
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
EXPOSE 8080
CMD java -jar /opt/service.jar