import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;

import java.util.Set;

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
        driver.get("https://airly.org/map/pl/#50.0626789849,19.9326583871");
        LocalStorage storage = ((ChromeDriver)driver).getLocalStorage();
        String map = storage.getItem("persist:map");
        storage.removeItem("persist:map");
        storage.setItem("some key", "some value");
        Set<String> keys = storage.keySet();
        int size = storage.size();
        storage.clear();
    }

    @Test
    public void test2() {
        driver.navigate().to("https://airly.eu/map/pl/#50.06237,19.93898");
        String key = "persist:map";

        String value = (String) ((JavascriptExecutor) driver)
                .executeScript("return localStorage.getItem(arguments[0]);", key);
        ((JavascriptExecutor) driver)
                .executeScript(
                        "localStorage.setItem(arguments[0], arguments[1]);", "some key", "some value");
        ((JavascriptExecutor) driver)
                .executeScript("localStorage.removeItem(arguments[0]);", key);
        String keyByIndex = (String) ((JavascriptExecutor) driver)
                .executeScript("return localStorage.key(arguments[0]);", 2);
        long size = (long) ((JavascriptExecutor) driver)
                .executeScript("return localStorage.length;");
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
    }
}