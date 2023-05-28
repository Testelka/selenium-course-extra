import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

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
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();
        devTools.getDomains().events().addJavascriptExceptionListener(System.out::println);
        driver.get("https://fakestore.testelka.pl/javascript-exceptions/");
        WebElement button1 = driver.findElement(By.id("button-1"));
        button1.click();
    }
}
