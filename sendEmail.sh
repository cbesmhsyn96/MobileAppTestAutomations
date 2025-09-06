#!/bin/bash

ZIP_PATH="${WORKSPACE}/allure-report.zip"
DOC_PATH="emailBody.docx"

source /Users/huseyinakcan/Desktop/sh_files/mail_env.sh

BOUNDARY="====MYBOUNDARY===="

ZIP_BASE64=$(base64 -w 0 "$ZIP_PATH")
DOC_BASE64=$(base64 -w 0 "$DOC_PATH")

EMAIL_CONTENT=$(cat <<EOF
From: ${MAIL_FROM}
To: ${MAIL_TO}
Subject: Mobile Test Automation Execution Completed
MIME-Version: 1.0
Content-Type: multipart/mixed; boundary="${BOUNDARY}"

--${BOUNDARY}
Content-Type: text/plain; charset="UTF-8"

Dear [Product Owner’s Name],

I would like to inform you that the login test automation suite has been executed successfully in Jenkins. 
The automated test run included the following test cases:

[TestCase_1-->UserStory_2] Verification of Opening the Login Screen and Basic Visibility
[TestCase_2-->UserStory_2] Verification of Form Fields Visibility on the Login Screen
[TestCase_3-->UserStory_2] Verification of Main Action Buttons Visibility on the Login Screen
[TestCase_4-->UserStory_2] Verification of Facebook and Google Login Buttons Visibility on the Login Screen
[TestCase_5-->UserStory_2] Verification of the Password Hide/Show Button Functionality on the Login Screen
[TestCase_6-->UserStory_2] Verification of the "Continue Without Signing Up" Button Functionality on the Login Screen
[TestCase_7-->UserStory_2] Verification of the "Forgot Password" Button Functionality on the Login Screen
[TestCase_8-->UserStory_2] Verification of Successful Login with Email on the Login Screen
[TestCase_9-->UserStory_2] Verification of Successful Login with Facebook Account on the Login Screen
[TestCase_10-->UserStory_2] Verification of Successful Login with Google Account on the Login Screen
[TestCase_11-->UserStory_2] Verification of Warning Messages When Logging in with Empty Email and Password on the Login Screen
[TestCase_12-->UserStory_2] Verification of Warning Displayed When Logging in with an Invalid Email on the Login Screen
[TestCase_13-->UserStory_2] Verification of Warning Displayed When Logging in with an Invalid Password on the Login Screen
...(other cases)

All scenarios were executed using the defined automation framework. Upon completion, an Allure Report has been generated, which provides a detailed overview of the test execution results, including passed and failed cases, execution timeline, and environment details.

This ensures that the application has been validated against the predefined test cases, and any findings are documented in the report.

Allure report is attached to the email.

Please let me know if you would like me to highlight specific results or provide additional insights from the execution.

Best regards,
Hüseyin Akcan
QA Engineer

--${BOUNDARY}
Content-Type: application/zip
Content-Disposition: attachment; filename="$(basename $ZIP_PATH)"
Content-Transfer-Encoding: base64

$ZIP_BASE64

--${BOUNDARY}
Content-Type: application/vnd.openxmlformats-officedocument.wordprocessingml.document
Content-Disposition: attachment; filename="$(basename $DOC_PATH)"
Content-Transfer-Encoding: base64

$DOC_BASE64

--${BOUNDARY}--
EOF
)

# Maili gönder
echo "$EMAIL_CONTENT" | curl --url "smtps://smtp.gmail.com:465" \
     --ssl-reqd \
     --mail-from "${MAIL_FROM}" \
     --mail-rcpt "${MAIL_TO}" \
     --user "${MAIL_FROM}:${APP_PASSWORD}" \
     -T -