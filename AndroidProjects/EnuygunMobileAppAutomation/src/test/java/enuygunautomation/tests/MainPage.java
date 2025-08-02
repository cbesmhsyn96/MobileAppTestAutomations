package enuygunautomation.tests;
import enuygunautomation.helper.*;

import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.util.Objects;

public class MainPage extends WaitHelper implements ITestExecution {
    /*Enuygun App açıldığında ilk görünen slide kontrol edilir
    Tüm seyahatin tek uygulamada,Biletim güvende,İlk bilen sen ol yazıları ve resimleri kontrol edilir.*/
    private void firstSlideControll() throws IOException {
        //Tüm seyahatin tek uygulamada
        String textOfElement =  Objects.requireNonNull(waitedElementUntilPresencable(//elementin xml de görünmesi için en fazla 10 saniye bekleyip elementi döndüren metot
                        getBy(Objects.requireNonNull(//type alıp By döndüren metot
                                getFoundedFileContainsKey("MainPageIntroTitle")//type ın bulunduğu metot
                        )))).getText();
        new Assertion().assertEquals(textOfElement,"Tüm seyahatin tek uygulamada");
        logger.info("Açılış yazısının '"+textOfElement+"' olduğu doğrulandı.");
        //Biletim güvende
        //İlk bilen sen ol


    }


    @Override
    public void testExecution() throws IOException {
        logger = LoggerFactory.getLogger(MainPage.class);
        //Buraya tüm Main Page senaryolarının metotları eklenecek.
        //Daha sonra TestManagement ile koşum yapılacak.
        firstSlideControll();
    }
}