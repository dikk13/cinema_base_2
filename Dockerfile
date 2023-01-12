FROM maven:3.8-openjdk-18 as MAVEN-BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:17-oracle

COPY --from=MAVEN-BUILD /target/cinema_base2.jar /cinema.jar


ENTRYPOINT ["java", "-jar", "cinema.jar"]