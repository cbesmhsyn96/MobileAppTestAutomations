package enuygunautomation.helper;

import enuygunautomation.base.BaseTest;
import enuygunautomation.env.Defines;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureAttachmentListener implements ITestListener {

    public AllureAttachmentListener() {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            if (Defines.androidDriver != null) {
                byte[] screenshot = ((TakesScreenshot) Defines.androidDriver)
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failed Step Screenshot", new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
