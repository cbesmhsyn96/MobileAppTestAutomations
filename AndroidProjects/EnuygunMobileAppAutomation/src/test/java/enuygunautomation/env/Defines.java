package enuygunautomation.env;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Defines {
    protected static Logger logger;
    protected static AndroidDriver androidDriver;
    protected static UiAutomator2Options androidOptions;
    protected static final List<File> fileList = List.of(Objects.requireNonNull(new File("src/test/resources/locators").listFiles()));
    protected static ObjectMapper objectMapper = new ObjectMapper();
    protected static WebDriverWait wait;
    protected static HashSet<Runnable> smokeTests = new HashSet<>();
    protected static HashSet<Runnable> regressionTests = new HashSet<>();

    /*protected static XCUITestOptions iosOptions
    protected static IOSDriver iosDriver*/

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
