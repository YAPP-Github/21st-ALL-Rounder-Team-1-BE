#!/bin/bash
echo "[HOLA_DEPLOYMENT_SYSTEM] Start Health Check"
echo "[HOLA_DEPLOYMENT_SYSTEM] curl -s http://localhost:3000/api/common/heartbeat"

for RETRY_COUNT in {1..15}
do
  RESPONSE=$(curl -s http://localhost:3000/api/common/heartbeat)
  UP_COUNT=$(echo $RESPONSE | grep 'OK' | wc -l)

  if [ $UP_COUNT -ge 1 ]
  then # $up_count >= 1 ("UP" 문자열이 있는지 검증)
      echo "[HOLA_DEPLOYMENT_SYSTEM] Success Health check!"
      break
  else
      echo "[HOLA_DEPLOYMENT_SYSTEM] Failed Health check"
      echo "[HOLA_DEPLOYMENT_SYSTEM] Health check: ${RESPONSE}"
  fi

  if [ $RETRY_COUNT -eq 10 ]
  then
    echo "[HOLA_DEPLOYMENT_SYSTEM] Failed Health check"
    exit 1
  fi

  echo "[HOLA_DEPLOYMENT_SYSTEM] Health check 연결 실패. 재시도..."
  sleep 10
done
exit 0
