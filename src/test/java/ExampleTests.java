import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
    public void test() throws MalformedURLException {
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#menu-item-88")).click();

//        driver.navigate().back();
//        driver.navigate().forward();
//        driver.navigate().refresh();
//        driver.navigate().to("http://localhost:8080/");
//        driver.navigate().to(new URL("http://localhost:8080/"));

        String pageSource = driver.getPageSource();

    }
}
