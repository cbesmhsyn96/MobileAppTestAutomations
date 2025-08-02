package enuygunautomation.helper;
import org.testng.annotations.*;

import java.io.*;

import static enuygunautomation.helper.PrepareTestPages.*;

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
