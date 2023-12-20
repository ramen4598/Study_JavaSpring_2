#!/bin/bash

# 스크립트가 실행되는 동안 오류가 발생하면 즉시 중단
set -e

# 로그 파일 경로 설정
LOG_FILE="rebuild.log"

# 기존 배포된 애플리케이션이 존재하는 경우에만 삭제
if [ -d "../../download/apache-tomcat-9.0.84/webapps/BookRentalPjt" ]; then
    rm -r ../../download/apache-tomcat-9.0.84/webapps/BookRentalPjt
fi

# Maven을 사용하여 프로젝트 빌드
mvn clean package

# 빌드된 애플리케이션을 Tomcat의 webapps 디렉토리로 복사
cp -r ./target/library-1.0.0-BUILD-SNAPSHOT ../../download/apache-tomcat-9.0.84/webapps/BookRentalPjt

# 로그 파일에 성공 메시지 기록
echo "rebuild success" > $LOG_FILE
