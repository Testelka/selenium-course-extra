import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ExampleTests {
    WebDriver driver;
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void test() {
        driver.get("http://localhost:3000/");
        JavascriptExecutor js = (JavascriptExecutor)driver;

        WebElement twitterLink = driver.findElement(By.cssSelector("a[href='https://twitter.com/ralllyco']"));
        js.executeScript("arguments[0].scrollIntoView();", twitterLink);
    }

    @Test
    public void test2() {
        driver.get("http://localhost:3000/");
        JavascriptExecutor js = (JavascriptExecutor)driver;

        WebElement button = driver.findElement(By.cssSelector("a.bg-primary-500"));
        String text = (String) js.executeScript("return arguments[0].innerText", button);

    }
}
