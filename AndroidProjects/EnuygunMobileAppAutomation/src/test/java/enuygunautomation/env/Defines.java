package enuygunautomation.env;

import enuygunautomation.LoginTest;
import enuygunautomation.helper.SwitchByLocatorElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Defines {
    protected static Logger logger = LoggerFactory.getLogger(LoginTest.class);
    protected static By testContext = By.id("com.mobilatolye.android.enuygun:id/text_fourthIntro_title");
    protected static AndroidDriver androidDriver = null;
    protected static UiAutomator2Options androidOptions = null;
    /*protected static XCUITestOptions iosOptions = null;
    protected static IOSDriver iosDriver = null;*/

    //null atamamın sebebi NullPointerException almamamak
    //NullPointerException riskini azaltmak için Optional yardımcı sınıfı da iş görür.
    //Optional<UiAutomator2Options> optionsAndroid ile de yapabilirdim. null atamazdım.
    //örneğin optionsAndroid kullanırken optional metotları ile devam edilirdi.

    /*
    static kullanılmasının buradaki sebebi
    field' a bu sınıfın nesnesi değil ismiyle erişmek.
    Defines sınıfı extend edildiği için Defines.logger
    gibi bir kullanım da engellenmiş oldu.
     */
}
