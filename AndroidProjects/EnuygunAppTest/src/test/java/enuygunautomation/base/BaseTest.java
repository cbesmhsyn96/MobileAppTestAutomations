package enuygunautomation.base;

import enuygunautomation.env.Defines;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.URI;
import java.time.Duration;

public class BaseTest extends Defines {
    @BeforeMethod
    public void setUpTest(ITestContext context){
        try {
            androidOptions = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setPlatformVersion("16")
                    .setUdid("emulator-5554")
                    .setAppPackage(appPackage)
                    .setAppActivity("com.mobilatolye.android.enuygun.features.application.MainActivity")
                    .setSkipUnlock(true)
                    .setNoReset(false)
                    .setAutoGrantPermissions(true)
                    .setAdbExecTimeout(Duration.ofMinutes(10))
                    .setNewCommandTimeout(Duration.ofMinutes(5))
                    .setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(120)) // Timeout değerini burada verdik
                    .setAppWaitActivity("*") // Eğer tüm activity’leri kabul etmesini istiyorsan "*"
                    .setAppWaitPackage(appPackage)
                    .setSkipDeviceInitialization(true);

            androidDriver = new AndroidDriver(
                    new URI("http://127.0.0.1:4723").toURL(), androidOptions);
            wait = new WebDriverWait(androidDriver, Duration.ofSeconds(30));
            context.setAttribute("driver", androidDriver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void tearDown(){
        logger.info("Test sonlandı.");
        androidDriver.quit();
    }
}
