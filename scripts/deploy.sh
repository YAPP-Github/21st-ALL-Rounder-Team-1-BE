#!/bin/bash
BUILD_PATH=$(ls /home/ec2-user/build/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
echo "[HOLA_DEPLOYMENT_SYSTEM] build file: $JAR_NAME"

echo "[HOLA_DEPLOYMENT_SYSTEM] copy build file"
DEPLOY_PATH=/home/ec2-user/
cp $BUILD_PATH $DEPLOY_PATH

echo "[HOLA_DEPLOYMENT_SYSTEM] change hola-api-deploy.jar"
CP_JAR_PATH=$DEPLOY_PATH$JAR_NAME
APPLICATION_JAR_NAME=hola-api-deploy.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

ln -Tfs $CP_JAR_PATH $APPLICATION_JAR

echo "[HOLA_DEPLOYMENT_SYSTEM] Running Application PID: "
CURRENT_PID=$(pgrep -f $APPLICATION_JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "[HOLA_DEPLOYMENT_SYSTEM] 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "[HOLA_DEPLOYMENT_SYSTEM] kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "[HOLA_DEPLOYMENT_SYSTEM] $APPLICATION_JAR 배포"
echo "PATH: $APPLICATION_JAR"
nohup java -jar "-Dspring.profiles.active=development" $APPLICATION_JAR > /dev/null 2> /dev/null < /dev/null &
