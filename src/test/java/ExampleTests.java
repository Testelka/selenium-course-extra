import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ExampleTests {
    WebDriver driver;
    String fileUrl;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quitDriver() {
        if (fileUrl != null) {
            driver.get(fileUrl);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".delete-attachment"))).click();
            driver.switchTo().alert().accept();
        }
        driver.quit();
    }

    @Test
    public void fileUpload() {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.cssSelector("#username")).sendKeys("admin");
        driver.findElement(By.cssSelector("#password")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();

        driver.get("http://localhost:8080/wp-admin/upload.php");
        driver.findElement(By.cssSelector("a.page-title-action")).click();
        driver.findElement(By.cssSelector("input[type=file]"))
                .sendKeys("/Users/ela/Code/resources/envelope.png");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[aria-label=\"envelope\"]"))).click();

        Assertions.assertEquals("File size: 2 MB", driver.findElement(
                By.cssSelector(".file-size")).getText());

        fileUrl = driver.getCurrentUrl();
    }
}
