import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FavoritesTest {
    public static void myFavorites(WebDriver driver, WebDriverWait wait, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchData"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchData"))).sendKeys(text);
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='searchBtn']"))).click();
                try {
                    String resultTextAll = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='resultText ']/h1"))).getText();
                    String[] resultTextSplt = resultTextAll.split(",");
                    System.out.println(resultTextAll);
                    for (String resultText : resultTextSplt) {
                        if (resultText.contains(text)) {
                            System.out.println(text + " kelimesi aranan sonuçlarda bulundu");
                        }
                    }
                    try {
                       Thread.sleep(5000);
                        WebElement pageNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='pagination'])/a[contains(text(),'2')]")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pageNumber);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='pagination'])/a[contains(text(),'2')]"))).click();
                        if(driver.getCurrentUrl().contains("Samsung&pg=2")){
                            System.out.println("Sayfa 2'ye Geçiş Yapıldı.");
                        }
                    try {
                        String productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='listView ']/ul/li[3])//h3[@class='productName ']"))).getText();
                        String addedProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='listView ']/ul/li[3])//span[@title='Favorilere ekle']"))).getText();
                        System.out.println(addedProduct);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='listView ']/ul/li[3])//span[@title='Favorilere ekle']"))).click();
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='btn btnBlack confirm']"))).click();
                    try {
                        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='menuTitle']")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", account);
                        Actions builder = new Actions(driver);
                        builder.moveToElement(account).build().perform();
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Favorilerim / Listelerim']"))).click();
                        try{
                            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.n11.com/hesabim/favorilerim']"))).click();
                            String favProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='productName ' and @xpath='1']"))).getText();
                            if(favProduct.contains(productName)){
                                System.out.println("Favorilere Doğru Ürün Eklenmiştir: " + favProduct);
                            }
                        try {
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[@class='deleteProFromFavorites']"))).click();
                            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='btn btnBlack confirm']"))).click();
                            String favoritesDeleted = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='emptyWatchList hiddentext']"))).getText();
                            if (favoritesDeleted.contains("ürün bulunmamaktadır.")){
                                System.out.println(favProduct + " Ürünü Başarılı Şekilde Silinmiştir.");
                            }

                        }catch (Exception e){
                            Assert.fail("Favorilere Eklenen Ürün Silinemedi");
                        }
                        }catch (Exception e){
                            Assert.fail("Favorilere Eklenen Ürün Hatalıdır");
                        }

                    }catch (Exception e){
                        Assert.fail("İstek Listem / Favorilerim'e Erişilemedi");
                    }
                    }catch (Exception e){
                        Assert.fail("Favorilere Ekle Butonuna Erişilemedi");
                    }
                    } catch (Exception e) {
                        Assert.fail("Page Bulunamadı");
                    }
                } catch (Exception e) {
                    Assert.fail("Arama Sonuçlarında: " + text + " bulunamadı.");
                }
            } catch (Exception e) {
                Assert.fail("Search Butonuna Erişilemedi");
            }
        } catch (Exception e) {
            Assert.fail("Search Alanına Erişilemedi");
        }
    }
}
