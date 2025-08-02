package enuygunautomation.helper;

import enuygunautomation.base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper extends BaseTest {
    protected static void waitAsImplicitly(int second){
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //protected static WebDriverWait waitAsExplicit
}
