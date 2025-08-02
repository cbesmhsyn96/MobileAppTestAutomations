package enuygunautomation.base;

import enuygunautomation.env.Defines;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URI;
import java.time.Duration;

public class BaseTest extends Defines {
    @BeforeTest
    public void setUpTest(){
        try {
            androidOptions = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setAppPackage("com.mobilatolye.android.enuygun")
                    .setUdid("emulator-5554")
                    .setAppActivity("com.mobilatolye.android.enuygun.features.application.MainActivity")
                    .setSkipUnlock(true)
                    .setNoReset(false);
            androidDriver = new AndroidDriver(
                    new URI("http://127.0.0.1:4723").toURL(), androidOptions);
            wait = new WebDriverWait(androidDriver, Duration.ofSeconds(10));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterTest
    public void tearDown(){
        logger.info("Test sonlandı.");
        androidDriver.quit();
    }
}
