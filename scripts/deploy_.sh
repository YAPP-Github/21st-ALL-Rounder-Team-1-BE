kill $(lsof -t -i:3000)
nohup java -jar "-Dspring.profiles.active=development" ../build/libs/hola-api-deploy-0.0.1-SNAPSHOT.jar