package enuygunautomation.tests.loginpage;

import com.google.common.collect.ImmutableMap;
import enuygunautomation.helper.WaitHelper;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

@Story("Kullanıcı login ekranını ve bileşenlerini görebilmeli, butonlara tıkladığında gerekli ekranlara yönlendirilmeli, başarılı ve başarısız login senaryolarını gerçekleştirmeli")
@Severity(SeverityLevel.CRITICAL)
@Owner("hakcanswtest")
public class LoginScreen extends WaitHelper {
    public LoginScreen() {
        logger =  LoggerFactory.getLogger(LoginScreen.class);
    }

    private void resetAppWithCleanFacebookLogin(){
        // Uygulama verisini temizle
        androidDriver.executeScript("mobile: shell", ImmutableMap.of(
                "command", "pm clear " + appPackage
        ));
        // Facebook uygulaması yüklüyse verisini temizle
        try {
            androidDriver.executeScript("mobile: shell", ImmutableMap.of(
                    "command", "pm clear com.facebook.katana"
            ));
        } catch (Exception e) {
            System.out.println("Facebook app yok, geçildi.");
        }
        // Chrome cache temizle (WebView login senaryoları için)
        try {
            androidDriver.executeScript("mobile: shell", ImmutableMap.of(
                    "command", "pm clear com.android.chrome"
            ));
        } catch (Exception e) {
            System.out.println("Chrome yok, geçildi.");
        }
        // Uygulamayı yeniden başlat
        androidDriver.activateApp(appPackage);
    }
    /*
    Login ekranının açıldığını kontrol.(Kayıt olda da)
    “Giriş yap“ ın seçili olduğunu görebilmeliyim.
    Eposta alanını kontrol.(Kayıt olda da)
    Şifre alanını kontrol.(Kayıt olda da)
    Şifre gizleme butonunu kontrol.(Kayıt olda da)
    “Üye olmadan devam et” butonunu kontrol.
    “Şifremi unuttum” butonunu kontrol.
    “Giriş yap” butonunu kontrol.(Kayıt olda Üye ol)
    “Facebook ile devam et” butonunu kontrol.(Kayıt olda da)
    “Google ile devam et” butonunu kontrol.(Kayıt olda da)
Buton aksiyonları
    Şifre gizleme butonuna tıklanınca parola alanına girilen değer gizlenmeli.(Kayıt olda da)
    “Üye olmadan devam et” butonuna tıklanınca “ENUYGUN bildirim izni“ popup'ı görünmeli.
    “Şifremi unuttum” butonuna tıklanınca “Şifremi unuttum“ popup'ı açılmalı.
    “Facebook ile devam et” butonuna tıklayınca yönlenme.(Kayıt olda da)
    “Google ile devam et” butonunu tıklayınca popup açılması.(Kayıt olda da)
     */
    @Test(priority = 1)
    @Description("[TestCase_1-->UserStory_2] Login Ekranının Açılması ve Temel Görünürlüğün Test Edilmesi")
    public void firstPartOfLoginSceeenControl() throws IOException, InterruptedException {
        logger.info("'Tüm seyahatin tek uygulamada' yazısı bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")));
        assertion.assertEquals(findElementByKey("MainPageSlideIntroTitle").getText(), "Tüm seyahatin tek uygulamada");
        logger.info("'Tüm seyahatin tek uygulamada' yazısı görüldü.");
        if (findElementByKey("MainPageSlideThirdCloseBtn").isDisplayed()){
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("ENUYGUN logosu görünene kadar bekleniyor.");
        //waitedElementUntilVisible(findElementByKey("MyAccount_AppNameLogo"));
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_AppNameLogo")));
        assertion.assertTrue(findElementByKey("MyAccount_AppNameLogo").isDisplayed());
        logger.info("ENUYGUN logosu göründü.");
        logger.info("'Giriş yap' menü butonunun seçili olduğu kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccount_LoginBtn").getAttribute("clickable").contains("false"));
        logger.info("'Giriş yap' menü butonunun seçili olduğu görüldü.");
    }

    @Test(priority = 2)
    @Description("[TestCase_2-->UserStory_2] Login Ekranında Form Alanlarının Görünürlüğünün Test Edilmesi")
    public void secondPartOfLoginSceeenControl() throws IOException {
        logger.info("'Tüm seyahatin tek uygulamada' yazısı bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")));
        assertion.assertEquals(findElementByKey("MainPageSlideIntroTitle").getText(), "Tüm seyahatin tek uygulamada");
        logger.info("'Tüm seyahatin tek uygulamada' yazısı görüldü.");
        if (findElementByKey("MainPageSlideThirdCloseBtn").isDisplayed()){
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");
        logger.info("'Giriş yap' ekranında şifre gizleme butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordHidder")));
        logger.info("'Giriş yap' ekranında şifre gizleme butonu görüldü.");
    }

    @Test(priority = 3)
    @Description("[TestCase_3-->UserStory_2] Login Ekranında Ana Aksiyon Butonlarının Görünürlüğünün Testi")
    public void thirdPartOfLoginSceeenControl() throws IOException {
        logger.info("'Tüm seyahatin tek uygulamada' yazısı bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")));
        assertion.assertEquals(findElementByKey("MainPageSlideIntroTitle").getText(), "Tüm seyahatin tek uygulamada");
        logger.info("'Tüm seyahatin tek uygulamada' yazısı görüldü.");
        if (findElementByKey("MainPageSlideThirdCloseBtn").isDisplayed()){
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("ENUYGUN logosu görünene kadar bekleniyor.");
        //waitedElementUntilVisible(findElementByKey("MyAccount_AppNameLogo"));
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_AppNameLogo")));
        assertion.assertTrue(findElementByKey("MyAccount_AppNameLogo").isDisplayed());
        logger.info("ENUYGUN logosu göründü.");
        logger.info("'Giriş yap' ekranında 'Giriş yap'(submit) butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginSubmitBtn")));
        logger.info("'Giriş yap' ekranında 'Giriş yap'(submit) butonu görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifremi unuttum' butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginIForgotPasswordBtn")));
        logger.info("'Giriş yap' ekranında 'Şifremi unuttum' butonu görüldü.");
        logger.info("'Giriş yap' ekranında 'Üye olmadan devam et' butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginUyeOlmadanDevamEtBtn")));
        logger.info("'Giriş yap' ekranında 'Üye olmadan devam et' butonu görüldü.");

    }

    @Test(priority = 4)
    @Description("[TestCase_4-->UserStory_2] Login Ekranında Facebook ve Google ile Giriş Butonlarının Görünürlüğünün Test Edilmesi")
    public void fourthPartOfLoginSceeenControl() throws IOException {
        logger.info("'Tüm seyahatin tek uygulamada' yazısı bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")));
        assertion.assertEquals(findElementByKey("MainPageSlideIntroTitle").getText(), "Tüm seyahatin tek uygulamada");
        logger.info("'Tüm seyahatin tek uygulamada' yazısı görüldü.");
        if (findElementByKey("MainPageSlideThirdCloseBtn").isDisplayed()){
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("ENUYGUN logosu görünene kadar bekleniyor.");
        //waitedElementUntilVisible(findElementByKey("MyAccount_AppNameLogo"));
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_AppNameLogo")));
        assertion.assertTrue(findElementByKey("MyAccount_AppNameLogo").isDisplayed());
        logger.info("ENUYGUN logosu göründü.");
        logger.info("'Giriş yap' ekranında 'Facebook ile devam et' butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginContinueWithFaceBook")));
        logger.info("'Giriş yap' ekranında 'Facebook ile devam et' butonu görüldü.");
        logger.info("'Giriş yap' ekranında 'Google ile devam et' butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginContinueWithGoogle")));
        logger.info("'Giriş yap' ekranında 'Google ile devam et' butonu görüldü.");
    }

    @Test(priority = 5)
    @Description("[TestCase_5-->UserStory_2] Login Ekranında Şifre Gizleme Butonunun İşlevselliğinin Testi")
    public void passwordHidderButtonFunctionControl() throws IOException {
        logger.info("'Tüm seyahatin tek uygulamada' yazısı bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")));
        assertion.assertEquals(findElementByKey("MainPageSlideIntroTitle").getText(), "Tüm seyahatin tek uygulamada");
        logger.info("'Tüm seyahatin tek uygulamada' yazısı görüldü.");
        if (findElementByKey("MainPageSlideThirdCloseBtn").isDisplayed()){
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("ENUYGUN logosu görünene kadar bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_AppNameLogo")));
        assertion.assertTrue(findElementByKey("MyAccount_AppNameLogo").isDisplayed());
        logger.info("ENUYGUN logosu göründü.");
        logger.info("'Giriş yap' menü butonunun seçili olduğu kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccount_LoginBtn").getAttribute("clickable").contains("false"));
        logger.info("'Giriş yap' menü butonunun seçili olduğu görüldü.");
        logger.info("'Giriş yap' ekranında şifre gizleme butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordHidder")));
        logger.info("'Giriş yap' ekranında şifre gizleme butonu görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanına "+validPssword+" giriliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput"))).sendKeys(validPssword);
        logger.info("'Giriş yap' ekranında 'Şifre' alanına "+validPssword+" girildi.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanının 'text' attribute unun value sunun "+validPssword+" olduğu kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccount_PasswordInput").getAttribute("text").contains(validPssword));
        logger.info("'Giriş yap' ekranında 'Şifre' alanının 'text' attribute unun value sunun "+validPssword+" olduğu görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanının 'password' attribute unun value sunun 'true' olduğu kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccount_PasswordInput").getAttribute("password").contains("true"));
        logger.info("'Giriş yap' ekranında 'Şifre' alanının 'password' attribute unun value sunun 'true' olduğu görüldü.");
        logger.info("'Giriş yap' ekranında şifre gizleme butonuna tıklanıyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordHidder"))).click();
        logger.info("'Giriş yap' ekranında şifre gizleme butona tıklandı.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanının 'password' attribute unun value sunun 'false' olduğu kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccount_PasswordInput").getAttribute("password").contains("false"));
        logger.info("'Giriş yap' ekranında 'Şifre' alanının 'password' attribute unun value sunun 'false' olduğu görüldü.");
    }

    @Test(priority = 6)
    @Description("[TestCase_6-->UserStory_2] Login Ekranında Üye Olmadan Devam Butonunun İşlevselliğinin Testi")
    public void uyeOlmdnDevamEtButtonFunctionControl() throws IOException {
        logger.info("'Tüm seyahatin tek uygulamada' yazısı bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideIntroTitle")));
        assertion.assertEquals(findElementByKey("MainPageSlideIntroTitle").getText(), "Tüm seyahatin tek uygulamada");
        logger.info("'Tüm seyahatin tek uygulamada' yazısı görüldü.");
        if (findElementByKey("MainPageSlideThirdCloseBtn").isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Üye olmadan devam et' butonuna tıklanıyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginUyeOlmadanDevamEtBtn"))).click();
        logger.info("'Üye olmadan devam et' butonuna tıklandı.");
        logger.info("Ana ekrana yönleniliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("HomeScreen_ENUYGUN_Text")));
        if (findElementByKey("HomeScreen_ENUYGUN_Text").isDisplayed())logger.info("Ana ekrana yönlenildi.");
    }

    @Test(priority = 7)
    @Description("[TestCase_7-->UserStory_2] Login Ekranında Şifremi Unuttum Butonunun İşlevselliğinin Testi")
    public void sifremiUnuttumButtonFunctionControl() throws IOException {
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Giriş yap' ekranında 'Giriş yap'(submit) butonu kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginSubmitBtn")));
        logger.info("'Giriş yap' ekranında 'Giriş yap'(submit) butonu görüldü.");
        logger.info("'Şifremi unuttum' butonuna tıklanıyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginIForgotPasswordBtn"))).click();
        logger.info("'Şifremi unuttum' butonuna tıklandı.");
        logger.info("Şifremi Unuttum pop-up' ının açıldığı kontrol ediliyor.");
        assertion.assertEquals(waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_SifreSifirla_Popup_Title"))).getText(),"Şifremi Unuttum");
        logger.info("Şifremi Unuttum pop-up' ının açıldığı görüldü.");
    }

    @Test(priority = 8)
    @Description("[TestCase_8-->UserStory_2] Login Ekranında E-posta ile Başarılı Giriş Testi")
    public void successLoginWithEposta() throws IOException {
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");
        logger.info("'E-posta' alanına "+validEmail+" giriliyor.");
        findElementByKey("MyAccount_LoginEmailInput").sendKeys(validEmail);
        logger.info("'E-posta' alanına "+validEmail+" girildi.");
        logger.info("'Şifre' alanına "+validPssword+" giriliyor.");
        findElementByKey("MyAccount_PasswordInput").sendKeys(validPssword);
        logger.info("'Şifre' alanına "+validPssword+" girildi.");
        logger.info("'Giriş yap'(submit) butonuna tıklanıyor.");
        findElementByKey("MyAccount_LoginSubmitBtn").click();
        logger.info("'Giriş yap'(submit) butonuna tıklandı.");
        logger.info("Ana ekranın açılması bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("HomeScreen_ENUYGUN_Text")));
        logger.info("Ana ekranın açıldı.");
        logger.info("Hesabım menüsüne gidiliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("HomeScreen_Hesabim_Tab"))).click();
        logger.info("Hesabım menüsüne gidildi.");
        logger.info("Hesabım menüsünde "+validEmail+" e-postası kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccountMenu_EmailText")));
        assertion.assertTrue(findElementByKey("MyAccountMenu_EmailText").getText().contains(validEmail));
        logger.info("Hesabım menüsünde "+validEmail+" e-postası olduğu doğrulandı.");
    }

    @Test(priority = 9)
    @Description("[TestCase_9-->UserStory_2] Login Ekranında Facebook Hesabı ile Başarılı Giriş Testi")
    public void successLoginWithFacebook() throws IOException, InterruptedException {
        resetAppWithCleanFacebookLogin();
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");
        logger.info("'Facebook ile devam et' butonuna tıklanıyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginContinueWithFaceBook")));
        findElementByKey("MyAccount_LoginContinueWithFaceBook").click();
        logger.info("'Facebook ile devam et' butonuna tıklandı.");
        logger.info("'Enhanced ad privacy in Chrome' ekranının açılması bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("FacebookLogin_PrivacyGotIt_Button")));
        logger.info("'Enhanced ad privacy in Chrome' ekranı açıldı.");
        logger.info("'Got it' butonuna tıklanıyor.");
        findElementByKey("FacebookLogin_PrivacyGotIt_Button").click();
        logger.info("'Got it' butonuna tıklandı.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("FacebookLogin_PrivacyGotIt_Button"))).click();
        logger.info("Facebook login ekranının açılması bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("FacebookLoginPage_EmailTextBox")));
        logger.info("Facebook login ekranı açıldı.");
        logger.info("Email alanına "+validFaceBookEmail+" giriliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("FacebookLoginPage_EmailTextBox"))).click();
        findElementByKey("FacebookLoginPage_EmailTextBox").sendKeys(validFaceBookEmail);
        assertion.assertTrue(findElementByKey("FacebookLoginPage_EmailTextBox").getText().equals(validFaceBookEmail));
        logger.info("Email alanında "+validFaceBookEmail+" görüldü.");
        logger.info("Password alanına "+validPssword+" giriliyor.");
        findElementByKey("FacebookLoginPage_PasswordTextBox").sendKeys(validPssword);
        assertion.assertTrue(findElementByKey("FacebookLoginPage_PasswordTextBox").getAttribute("text").equals(validPssword));
        logger.info("Password alanında "+validPssword+" görüldü.");
        logger.info("'Log in' butonuna tıklanıyor.");
        findElementByKey("FacebookLoginPage_LoginButton").click();
        logger.info("'Log in' butonuna tıklandı.");
        logger.info("'Hesabım menüsüne gidiliyor.'");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("HomeScreen_Hesabim_Tab"))).click();
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccountMenu_EmailText")));
        logger.info("'Hesabım menüsüne gidildi.'");
        logger.info("Hesabım menüsünde Facebook ile girişte kullanılan "+validFaceBookEmail+" e-postası kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccountMenu_EmailText").getText().equals(validFaceBookEmail));
        logger.info("Hesabım menüsünde Facebook ile girişte kullanılan "+validFaceBookEmail+" e-postası görüldü.");
    }

    @Test(priority = 16)
    @Description("[TestCase_10-->UserStory_2] Login Ekranında Google Hesabı ile Başarılı Giriş Testi")
    public void successLoginWithGoogle() throws IOException, InterruptedException {
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");
        logger.info("'Google ile devam et' butonuna tıklanıyor.");
        findElementByKey("MyAccount_LoginContinueWithGoogle").click();
        logger.info("'Google ile devam et' butonuna tıklandı.");
        logger.info("'Choose an account' pop-up' ının açılması bekleniyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginWithGoogle_AppIcon")));
        logger.info("'Choose an account' pop-up' ı açıldı.");
        logger.info("'Choose an account' pop-up' ında "+validEmail+" hesabı seçiliyor.");
        findElementByKey("MyAccount_LoginWithGoogle_AccountOptFirst").click();
        logger.info("'Choose an account' pop-up' ında "+validEmail+" hesabı seçildi.");
        logger.info("'Hesabım menüsüne gidiliyor.'");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("HomeScreen_Hesabim_Tab"))).click();
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccountMenu_EmailText")));
        logger.info("'Hesabım menüsüne gidildi.'");
        logger.info("Hesabım menüsünde Google hesabı seçilen "+validEmail+" e-postası kontrol ediliyor.");
        assertion.assertTrue(findElementByKey("MyAccountMenu_EmailText").getText().equals(validEmail));
        logger.info("Hesabım menüsünde Google hesabı seçilen "+validEmail+" e-postası görüldü.");
    }

    @Test(priority = 11)
    @Description("[TestCase_11-->UserStory_2] Login Ekranda Boş E-posta ve Şifre ile Girişte Gelen Uyarı Mesajlarının Testi")
    public void emptyEmailAndPasswordWarnings() throws IOException {
        // Ortak adım: Açılış slaytını kapatma
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }

        // Ortak adım: Giriş ekranında email alanı kontrolü
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");

        // Ortak adım: Giriş ekranında şifre alanı kontrolü
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");

        // Adım: Giriş yap butonuna tıklama
        logger.info("'Giriş yap' butonuna tıklanıyor.");
        findElementByKey("MyAccount_LoginSubmitBtn").click();

        // Adım: E-posta alanı uyarısının kontrolü
        logger.info("E-posta alanı uyarı mesajı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_Eposta_WarnMessage")));
        assertion.assertTrue(findElementByKey("MyAccount_Login_Eposta_WarnMessage")
                .getText().equals("Lütfen e-posta adresinin doğruluğundan emin olun."));
        logger.info("E-posta alanı için 'Lütfen e-posta adresinin doğruluğundan emin olun.' mesajı görüldü.");

        // Adım: Şifre alanı uyarısının kontrolü
        logger.info("Şifre alanı uyarı mesajı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_Password_Valid_WarnMessage")));
        assertion.assertTrue(findElementByKey("MyAccount_Login_Password_Valid_WarnMessage")
                .getText().equals("Lütfen geçerli bir şifre girin."));
        logger.info("Şifre alanı için 'Lütfen geçerli bir şifre girin.' mesajı görüldü.");
    }

    @Test(priority = 12)
    @Description("[TestCase_12-->UserStory_2] Login Ekranında Geçersiz E-posta ile Girişte Görünen Uyarının Testi")
    public void invalidEmailWarningMessage() throws IOException {
        // Ortak adım: Açılış slaytını kapatma
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }

        // Ortak adım: Giriş ekranında email alanı kontrolü
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");

        // Ortak adım: Giriş ekranında şifre alanı kontrolü
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");

        // Adım: Geçersiz e-posta girme
        String invalidEmail = "invalidEmailFormat";
        logger.info("E-posta alanına geçersiz e-posta giriliyor: " + invalidEmail);
        findElementByKey("MyAccount_LoginEmailInput").sendKeys(invalidEmail);
        logger.info("E-posta alanına geçersiz e-posta girildi.");

        // Adım: Giriş yap butonuna tıklama
        logger.info("'Giriş yap' butonuna tıklanıyor.");
        findElementByKey("MyAccount_LoginSubmitBtn").click();

        // Adım: E-posta alanı uyarısının kontrolü
        logger.info("E-posta alanı uyarı mesajı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_Eposta_WarnMessage")));
        assertion.assertTrue(findElementByKey("MyAccount_Login_Eposta_WarnMessage")
                .getText().equals("Lütfen e-posta adresinin doğruluğundan emin olun."));
        logger.info("E-posta alanı için 'Lütfen e-posta adresinin doğruluğundan emin olun.' mesajı görüldü.");
    }

    @Test(priority = 13)
    @Description("[TestCase_13-->UserStory_2] Login Ekranında Geçersiz Şifre ile Girişte Görünen Uyarının Testi")
    public void invalidPasswordWarningMessage() throws IOException {
        // Ortak adım: Açılış slaytını kapatma
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }

        // Ortak adım: Giriş ekranında email alanı kontrolü
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");

        // Ortak adım: Giriş ekranında şifre alanı kontrolü
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");

        // Adım: Geçersiz şifre girme
        String invalidPassword = "aSD-*";
        logger.info("Şifre alanına geçersiz şifre giriliyor: " + invalidPassword);
        findElementByKey("MyAccount_PasswordInput").sendKeys(invalidPassword);
        assertion.assertTrue(findElementByKey("MyAccount_PasswordInput").getAttribute("text").equals(invalidPassword));
        logger.info("Şifre alanına girilen '" + invalidPassword + "' değeri görüldü.");

        // Adım: Giriş yap butonuna tıklama
        logger.info("'Giriş yap' butonuna tıklanıyor.");
        findElementByKey("MyAccount_LoginSubmitBtn").click();

        // Adım: Şifre alanı uyarısının kontrolü
        logger.info("Şifre alanı uyarı mesajı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_Password_Valid_WarnMessage")));
        assertion.assertTrue(findElementByKey("MyAccount_Login_Password_Valid_WarnMessage")
                .getText().equals("Lütfen geçerli bir şifre girin."));
        logger.info("Şifre alanı için 'Lütfen geçerli bir şifre girin.' mesajı görüldü.");
    }

    @Test(priority = 14)
    @Description("[TestCase_14-->UserStory_2] Login Ekranında Geçersiz E-posta ve Şifre ile Girişte Görünen Uyarıların Testi")
    public void invalidEmailAndPasswordWarnings() throws IOException {
        // Ortak adım: Açılış slaytını kapatma
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }

        // Ortak adım: Giriş ekranında email alanı kontrolü
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");

        // Ortak adım: Giriş ekranında şifre alanı kontrolü
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");

        // Adım: Geçersiz e-posta girme
        String invalidEmail = "hsynakcn0mobile1test@gma";
        logger.info("E-posta alanına geçersiz e-posta giriliyor: " + invalidEmail);
        findElementByKey("MyAccount_LoginEmailInput").sendKeys(invalidEmail);
        assertion.assertTrue(findElementByKey("MyAccount_LoginEmailInput").getText().equals(invalidEmail));
        logger.info("E-posta alanına girilen '" + invalidEmail + "' değeri görüldü.");

        // Adım: Geçersiz şifre girme
        String invalidPassword = "aSD-*";
        logger.info("Şifre alanına geçersiz şifre giriliyor: " + invalidPassword);
        findElementByKey("MyAccount_PasswordInput").sendKeys(invalidPassword);
        assertion.assertTrue(findElementByKey("MyAccount_PasswordInput").getAttribute("text").equals(invalidPassword));
        logger.info("Şifre alanına girilen '" + invalidPassword + "' değeri görüldü.");

        // Adım: Giriş yap butonuna tıklama
        logger.info("'Giriş yap' butonuna tıklanıyor.");
        findElementByKey("MyAccount_LoginSubmitBtn").click();

        // Adım: E-posta alanı uyarısının kontrolü
        logger.info("E-posta alanı uyarı mesajı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_Eposta_WarnMessage")));
        assertion.assertTrue(findElementByKey("MyAccount_Login_Eposta_WarnMessage")
                .getText().equals("Lütfen e-posta adresinin doğruluğundan emin olun."));
        logger.info("E-posta alanı için 'Lütfen e-posta adresinin doğruluğundan emin olun.' mesajı görüldü.");

        // Adım: Şifre alanı uyarısının kontrolü
        logger.info("Şifre alanı uyarı mesajı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_Login_Password_Valid_WarnMessage")));
        assertion.assertTrue(findElementByKey("MyAccount_Login_Password_Valid_WarnMessage")
                .getText().equals("Lütfen geçerli bir şifre girin."));
        logger.info("Şifre alanı için 'Lütfen geçerli bir şifre girin.' mesajı görüldü.");
    }

    @Test(priority = 15)
    @Description("[TestCase_15-->UserStory_2] Login Ekranında Facebook ile Geçersiz Bilgilerle Girişte Görünen Hata Popup’ ının Kontrolü Testi")
    public void asd() throws IOException, InterruptedException {
        resetAppWithCleanFacebookLogin();
        if (waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MainPageSlideThirdCloseBtn"))).isDisplayed()) {
            logger.info("Açılış slaytı kapatma butonu görüldü.");
            findElementByKey("MainPageSlideThirdCloseBtn").click();
            logger.info("Açılış slaytı kapatma butonuna tıklandı.");
        }
        logger.info("'Giriş yap' ekranında 'E-posta' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginEmailInput")));
        logger.info("'Giriş yap' ekranında 'E-posta' alanı görüldü.");
        logger.info("'Giriş yap' ekranında 'Şifre' alanı kontrol ediliyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_PasswordInput")));
        logger.info("'Giriş yap' ekranında 'Şifre' alanı görüldü.");
        logger.info("'Facebook ile devam et' butonuna tıklanıyor.");
        waitedElementUntilPresencable(getBy(getFoundedFileContainsKey("MyAccount_LoginContinueWithFaceBook")));
        findElementByKey("MyAccount_LoginContinueWithFaceBook").click();
        logger.info("'Facebook ile devam et' butonuna tıklandı.");
        Thread.sleep(10000);

    }







}