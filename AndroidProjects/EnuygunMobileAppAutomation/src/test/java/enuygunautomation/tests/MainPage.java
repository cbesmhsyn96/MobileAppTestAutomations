package enuygunautomation.tests;
import enuygunautomation.env.Defines;
import enuygunautomation.helper.ITestExecution;
import enuygunautomation.helper.WaitHelper;
import org.openqa.selenium.WebElement;

import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class MainPage extends WaitHelper implements ITestExecution {
    /*Enuygun App açıldığında ilk görünen slide kontrol edilir
    Tüm seyahatin tek uygulamada,Biletim güvende,İlk bilen sen ol yazıları ve resimleri kontrol edilir.*/
    private void firstSlideControll(){
        waitAsImplicitly(10);
        WebElement element = androidDriver.findElement(testContext);
        new Assertion().assertEquals(element.getText(),"Tüm seyahatin tek uygulamada");
        logger.info("Açılış yazısının '"+element.getText()+"' olduğu doğrulandı.");
        waitAsImplicitly(10);
    }

    @Override
    public void testExecution() {
        logger = LoggerFactory.getLogger(MainPage.class);
        //Buraya tüm Main Page senaryolarının metotları eklenecek.
        //Daha sonra TestManagement ile koşum yapılacak.
        firstSlideControll();
    }
}