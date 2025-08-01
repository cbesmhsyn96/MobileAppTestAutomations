package enuygunautomation.tests;
import enuygunautomation.helper.WaitHelper;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class MainPage extends WaitHelper {
    /*Enuygun App açıldığında ilk görünen slide kontrol edilir
    Tüm seyahatin tek uygulamada,Biletim güvende,İlk bilen sen ol yazıları ve resimleri kontrol edilir.*/
    @Test
    public void firstSlideControll(){
        waitAsImplicitly(10);
        WebElement element = androidDriver.findElement(testContext);
        new Assertion().assertEquals(element.getText(),"Tüm seyahatin tek uygulamada");
        logger.info("Açılış yazısının '"+element.getText()+"' olduğu doğrulandı.");
        waitAsImplicitly(10);
    }

}