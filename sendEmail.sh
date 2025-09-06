#!/bin/bash

buildNumber="${BUILD_NUMBER}" 
zipUrl="http://localhost:8080/job/EnuygunAppTestAutomation_Android/${buildNumber}/artifact/allure-report.zip"

source /Users/huseyinakcan/Desktop/sh_files/mail_env.sh && \
curl -o allure-report.zip ${zipUrl} && \
curl -url "smtp://smtp.gmail.com:465" \
--ssl-reqd \
--mail-from "${MAIL_FROM}" \
--mail-rcpt "${MAIL_TO}" \
--upload-file allure-report.zip \
--upload-file emailBody.txt \
--user "${MAIL_FROM}:${APP_PASSWORD}"