kill $(lsof -t -i:3000)
./gradlew clean build
nohup java -jar "-Dspring.profiles.active=development" ./build/libs/hola-api-deploy-0.0.1-SNAPSHOT.jar