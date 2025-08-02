package enuygunautomation.helper;

import enuygunautomation.tests.MainPage;
import org.junit.jupiter.api.Disabled;
import org.testng.annotations.Test;

import java.io.IOException;

import static enuygunautomation.helper.PrepareTestPages.mainPage;
import static enuygunautomation.helper.PrepareTestPages.prepareAllPagesTests;

public class TestExecutionManager extends WaitHelper{
    @Test
    //@Disabled
    public void allPagesTestExecution() throws IOException {
        prepareAllPagesTests();
        mainPage.testExecution();
    }


    /*
    Regresyon ve smoke için ayrı metot. Orda parametre geçirme gibi güncellemeler olabilir veya
    regresyon smoke için toplayıcı metot da yazılabilir.
     */
}
