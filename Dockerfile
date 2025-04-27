FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11.0.16
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/com.juansanz.themoviedb-server-0.0.1.jar
ENTRYPOINT ["java","-jar","/app/com.juansanz.themoviedb-server-0.0.1.jar"]