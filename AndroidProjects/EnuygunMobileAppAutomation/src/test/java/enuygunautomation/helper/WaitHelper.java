package enuygunautomation.helper;

import enuygunautomation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper extends SwitchByLocatorElement {
    protected static void waitAsImplicitly(int second){
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected static WebElement waitedElementUntilPresencable(By by){
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    protected static WebElement waitedElementUntilClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected static WebElement waitedElementUntilVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /*
    Bir bekleme stratejisi çalışmadığında diğerleri ile denenebilir.
     */


    //protected static WebDriverWait waitAsExplicit
}
