package enuygunautomation;
import enuygunautomation.base.BaseTest;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class LoginTest extends BaseTest {

    /*Enuygun App açıldığında ilk görünen slide kontrol edilir
    Tüm seyahatin tek uygulamada,
    Biletim güvende,
    İlk bilen sen ol yazıları ve resimleri kontrol edilir.*/
    @Test
    public void firstSledeControll(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement element = driver.findElement(testContext);
        new Assertion().assertEquals(element.getText(),"Tüm seyahatin tek uygulamada");
        logger.info("Açılış yazısının '"+element.getText()+"' olduğu doğrulandı.");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


}