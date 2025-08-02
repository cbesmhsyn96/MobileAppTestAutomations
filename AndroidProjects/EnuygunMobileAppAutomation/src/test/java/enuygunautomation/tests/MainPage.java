package enuygunautomation.tests;
import enuygunautomation.env.Defines;
import enuygunautomation.helper.ITestExecution;
import enuygunautomation.helper.SwitchByLocatorElement;
import enuygunautomation.helper.WaitHelper;
import org.openqa.selenium.WebElement;

import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

public class MainPage extends SwitchByLocatorElement implements ITestExecution {
    /*Enuygun App açıldığında ilk görünen slide kontrol edilir
    Tüm seyahatin tek uygulamada,Biletim güvende,İlk bilen sen ol yazıları ve resimleri kontrol edilir.*/
    private void firstSlideControll() throws IOException {
        waitAsImplicitly(10);
        String testOfElement =  findElementByKey(searchedKey).getText();
        new Assertion().assertEquals(testOfElement,"Tüm seyahatin tek uygulamada");
        logger.info("Açılış yazısının '"+testOfElement+"' olduğu doğrulandı.");
        waitAsImplicitly(10);
    }

    @Override
    public void testExecution() throws IOException {
        logger = LoggerFactory.getLogger(MainPage.class);
        //Buraya tüm Main Page senaryolarının metotları eklenecek.
        //Daha sonra TestManagement ile koşum yapılacak.
        firstSlideControll();
    }
}