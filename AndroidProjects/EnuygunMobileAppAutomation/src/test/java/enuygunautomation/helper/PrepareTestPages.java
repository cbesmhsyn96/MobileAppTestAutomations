package enuygunautomation.helper;

import enuygunautomation.tests.MainPage;

public class PrepareTestPages {
    protected static MainPage mainPage;

    protected static void prepareAllPagesTests(){
        mainPage = new MainPage();
    }

}
