FROM ubuntu:latest AS buld

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
Run mvn clean install

EXPOSE 8080 

COPY --from=buld /target/todolist-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]