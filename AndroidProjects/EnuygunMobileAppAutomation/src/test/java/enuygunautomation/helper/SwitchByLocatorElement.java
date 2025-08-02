package enuygunautomation.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import enuygunautomation.base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import java.io.*;
import java.util.*;

public class SwitchByLocatorElement extends BaseTest {
    /*
    Burada xpath, id, class gibi attributelar için By döndüren metot yazılıp
    dönen By ı androidDriver.findElement(dönenBy) şeklinde WebElement döndüren bir
    swither yapı kuracağım. Bu da 2. metot olacak.
    (getBy metodu ile yapılır. Parametre olarak getFoundedFileContainsKey metodunun döndürdüğü değeri alır.)
    readable -- compact yapı elde edildi.

    Bu sınıf bir Json dosyasına ihtiyaç duyacak.

    Json deserialization
    kodlarını bu sınıf içine veya başka bir helper sınıf içinde yazabilirim.
    Jackson-databind ağır json verileri ile çalışımadığı zaman kullanılabilecek bir bağımlılık.
    Alternatif olarak Java' nın dahilinde bulunan bir sınıf ta kullanılabilirdi(ağır jsonlar için).
    Ben burada Jackson-databind kullanacağım.
     */

    protected static DeserialLocatorFiles getFoundedFileContainsKey(String searchedKey) throws IOException {
        for (File currentLocation:fileList){
            if(currentLocation.isDirectory()){
                File[] currentFiles = Objects.requireNonNull(currentLocation.listFiles());
                //src/test/resources/locators altındaki locatorların tutulduğu json dosyaları geziliyor.
                for (File currentJsonFile : currentFiles){
                    //gezilen her bir dosyadaki tüm nodelar readValue yöntemi ile okunuyor.
                    List<DeserialLocatorFiles> jsonNodesInCurrentFile = objectMapper.readValue(currentJsonFile, new TypeReference<List<DeserialLocatorFiles>>() {});
                    //şu an okunan json dosyasındaki tüm json node ları geziliyor.
                    for (DeserialLocatorFiles currentJsonNode : jsonNodesInCurrentFile){
                        //key alanının valuesu searchedKey olan json node u aranıyor.
                        if(currentJsonNode.getKey().equals(searchedKey)){
                            //aranan elementin ismi yani "key" i searchedKey olan json node u döndürülüyor.
                            return currentJsonNode;
                        }
                    }
                }
            }
        }
        return null;
    }

    protected static By getBy(DeserialLocatorFiles deserialLocatorFiles){
        String value = deserialLocatorFiles.getValue();
        String type = deserialLocatorFiles.getType();
        switch (type){
            case "id": return By.id(value);
            case "xpath": return By.xpath(value);
            case "class": return By.className(value);
            case "accessibilityId": return AppiumBy.accessibilityId(value);
            case "androidUIautomator": return AppiumBy.androidUIAutomator(value);
            /*
            eklenebilir
             */
        }
        return null;
    }

    protected static WebElement findElementByKey(String searchedKey) throws IOException {
        By by;
        Optional<By> byOptional = Optional.ofNullable(getBy(Objects.requireNonNull(getFoundedFileContainsKey(searchedKey))));
        if(byOptional.isPresent()){
            by = byOptional.get();
            return androidDriver.findElement(by);
        }else{
            logger.info("{\"key\" : \""+searchedKey+"\""+" bulunamadı.");
        }
        return null;
    }
}