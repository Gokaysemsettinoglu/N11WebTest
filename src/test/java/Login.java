import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login {
    public static void signIn(WebDriver driver, WebDriverWait wait, String email, String pass) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btnSignIn']"))).click(); // SİGN IN
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).clear();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(pass);
                    try {
                        Thread.sleep(2000);
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginButton']"))).click();
                    } catch (Exception e) {
                        Assert.fail("Login Butonuna Erişilemedi");
                    }
                } catch (Exception e) {
                    Assert.fail("Password Alanına Erişim Sağlanamadı");
                }
            } catch (Exception e) {
                Assert.fail("Email Alanına Erişilemedi");
            }
        } catch (Exception e) {
            Assert.fail("Giriş Yap Butonuna Erişilemedi.");
        }
    }
}
