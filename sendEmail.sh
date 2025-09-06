#!/bin/bash

ZIP_PATH="${WORKSPACE}/allure-report.zip"

source /Users/huseyinakcan/Desktop/sh_files/mail_env.sh && \

curl --url "smtps://smtp.gmail.com:465" \
--ssl-reqd \
--mail-from "${MAIL_FROM}" \
--mail-rcpt "${MAIL_TO}" \
--upload-file "${ZIP_PATH}" \
--upload-file "emailBody.docx" \
--user "${MAIL_FROM}:${APP_PASSWORD}"