package enuygunautomation.helper;

import enuygunautomation.base.BaseTest;

import java.time.Duration;

public class WaitHelper extends BaseTest {
    protected static void waitAsImplicitly(int second){
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
