import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RunTest {

    WebDriver driver;
    WebDriverWait wait;
    String browserType = "webdriver.gecko.driver";
    String browserPath = "drivers/geckodriver.exe";
    String pageURL = "https://www.n11.com/";
    String email = "bijocip623@veb34.com";
    String pass = "123qwe123";
    String text = "Samsung";

    @BeforeClass
    public void startBrowser() {
        System.setProperty(browserType, browserPath);
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 12);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            driver.get(pageURL);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current Url : " + currentUrl);
            if (pageURL.contains(currentUrl)) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='myLocation-close-info']"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='dn-slide-deny-btn']"))).click();
            }
        } catch (Exception e) {
            Assert.fail("Current Url ile Page Url Aynı Değil");
        }
    }

    @Test(priority = 1)
    public void runLogin() {
        Login.signIn(driver, wait, email, pass);
    }

    @Test(priority = 2)
    public void runFavorites() {
        FavoritesTest.myFavorites(driver, wait, text);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
