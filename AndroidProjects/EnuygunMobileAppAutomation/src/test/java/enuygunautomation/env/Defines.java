package enuygunautomation.env;

import enuygunautomation.LoginTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defines {
    protected static Logger logger = LoggerFactory.getLogger(LoginTest.class);
    protected static By testContext = By.id("com.mobilatolye.android.enuygun:id/text_fourthIntro_title");
    protected static AndroidDriver driver = null;
    protected static UiAutomator2Options options = null;
}
