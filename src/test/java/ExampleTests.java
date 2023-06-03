import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExampleTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
    @Test
    public void test() {
        driver.get("http://localhost:8080/product/calculus-made-easy-by-silvanus-p-thompson/");
        String originalWindows = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        //driver.switchTo().newWindow(WindowType.WINDOW);
        //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String newWindow = driver.getWindowHandle();
        driver.get("http://localhost:8080/");
        driver.switchTo().window(originalWindows);
        driver.get("http://localhost:8080/wishlist/");
        driver.switchTo().window(newWindow);
        driver.close();
    }
}