#!/bin/bash

# PATCH 요청을 보내는 함수
send_patch_request() {
  curl -X PATCH "http://localhost:8080/deposit/1" \
  -H "Content-Type: application/json" \
  -d '{"amount": 1}' &
}

# 총 10번의 요청 그룹을 보냄
for i in {1..10}
do
  echo "Sending batch $i"

  # 10개의 요청을 동시에 보냄
  for j in {1..10}
  do
    send_patch_request
  done

  # 모든 백그라운드 작업이 끝날 때까지 대기
  wait
done

echo "All requests sent."

