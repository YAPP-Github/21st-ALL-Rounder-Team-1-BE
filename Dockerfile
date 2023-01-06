FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local", "/app.jar"]
# Docker 는 Local App 서버만 띄우기 때문에 profile local 지정