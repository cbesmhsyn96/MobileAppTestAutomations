package enuygunautomation.base;

import enuygunautomation.env.Defines;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URI;

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
