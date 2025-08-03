package enuygunautomation.tests;
import enuygunautomation.helper.*;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

public class MainPage extends WaitHelper implements ITestExecute {

    private void tumSeyahatinTekUygulamadaControl() throws IOException {
        //Tüm seyahatin tek uygulamada
        //Bold yazıyı kontrol
        String textOfElement = Objects.requireNonNull(waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")))).getText();
        //try catch içine alındığında o adım başarısız olsa da devam eder.
        try {
            assertion.assertEquals(textOfElement,"Tüm seyahatin tek uygulamada");
            logger.info("'"+textOfElement+"' yazısı görüldü.");
        }catch (AssertionError e){
            logger.error("'"+textOfElement+"' yazısı görülmedi.");
        }

        //Tüm seyahatin tek uygulamada image kontrolü
        assertion.assertTrue(waitedElementUntilPresencable(getBy(Objects.requireNonNull(getFoundedFileContainsKey("MainPageSlideIntroImage")))).isDisplayed());
        logger.info("MainPageSlideIntroImage görüldü.");

        //Tüm seyahatin tek uygulamada açıklama yazısı kontrolü
        assertion.assertEquals(waitedElementUntilVisible(findElementByKey("MainPageSlideIntroText")).getText(),"Uçak bileti, otobüs bileti, otel, araç kiralama ve transfer hepsi aynı uygulamada!");
        logger.info("Uçak bileti, otobüs bileti, otel,... yazısı görüldü.");
        //Biletim güvende alanına kaydırma işlemi
        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var start = new Point(903, 739);
        var end = new Point(136, 716);
        var swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(Arrays.asList(swipe));
        logger.info("Biletim güvende alanına kaydırıldı.");
    }

    private void biletimGuvendeControll() throws IOException {
        //Biletim güvende
        assertion.assertEquals(Objects.requireNonNull(waitedElementUntilPresencable(getBy(Objects.requireNonNull(getFoundedFileContainsKey("MainPageSlideSecondTitle"))))).getText(),"Biletim güvende");
        logger.info("'"+Objects.requireNonNull(waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideSecondTitle")))).getText()+"' yazısı görüldü.");


        //Biletim güvende image kontrolü
        assertion.assertTrue(waitedElementUntilPresencable(getBy(Objects.requireNonNull(getFoundedFileContainsKey("MainPageSlideSecondImage")))).isDisplayed());
        logger.info("MainPageSlideSecondImage görüldü.");

        //Biletim güvende yazısı kontrolü
        assertion.assertEquals(waitedElementUntilVisible(findElementByKey("MainPageSlideSecondText")).getText(),"Uçuşa 3 saate kadar ki bilet iptallerinde\n" +
                "%90 iade için öncelikli hizmet al.");
        logger.info("Uçuşa 3 saate kadar ki bilet iptallerinde... yazısı görüldü.");

        //İlk bilen sen ol alanına kaydırma işlemi
        final var finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        var start2 = new Point(961, 840);
        var end2 = new Point (101, 840);
        var swipe2 = new Sequence(finger2, 1);
        swipe2.addAction(finger2.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start2.getX(), start2.getY()));
        swipe2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe2.addAction(finger2.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end2.getX(), end2.getY()));
        swipe2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(Arrays.asList(swipe2));
        logger.info("İlk bilen sen ol alanına kaydırıldı.");
    }

    private void ilkBilenSenOlControll() throws IOException {
        //İlk bilen sen ol
        waitAsImplicitly(20);
        WebElement element = findElementByKey("MainPageSlideThirdTitle");
        assertion.assertEquals(Objects.requireNonNull(element).getText(),"İlk bilen sen ol!");
        logger.info("'"+waitedElementUntilVisible(findElementByKey("MainPageSlideThirdTitle")).getText()+"' yazısı görüldü.");


        //İlk bilen sen ol image kontrolü
        assertion.assertTrue(waitedElementUntilPresencable(getBy(Objects.requireNonNull(getFoundedFileContainsKey("MainPageSlideThirdImage")))).isDisplayed());
        logger.info("MainPageSlideThirdImage görüldü.");

        //İlk bilen sen ol yazısı kontrolü
        assertion.assertEquals(waitedElementUntilVisible(findElementByKey("MainPageSlideThirdText")).getText(),"Enuygun’un özel kampanyalarından ve\n fırsat uçuşlarından haberdar olmak için\n bildirimlere izin ver.");
        logger.info("Enuygun’un özel kampanyalarından ve... yazısı görüldü.");

        //Bildirimlere izin ver butonu kontrol
        //Daha sonra hatırlat linki kontrol

    }
    /*Enuygun App açıldığında ilk görünen slide kontrol edilir
    Tüm seyahatin tek uygulamada,Biletim güvende,İlk bilen sen ol yazıları ve resimleri kontrol edilir.*/

    private void firstSlideControll() throws IOException {
        tumSeyahatinTekUygulamadaControl();
        biletimGuvendeControll();
        ilkBilenSenOlControll();


    }


    @Override
    public void testExecute() throws IOException {
        logger = LoggerFactory.getLogger(MainPage.class);
        //Buraya tüm Main Page senaryolarının metotları eklenecek.
        //Daha sonra TestManagement ile koşum yapılacak.
        firstSlideControll();
    }
}