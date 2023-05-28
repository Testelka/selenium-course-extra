import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v113.log.Log;

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
        devTools.send(Log.enable());
        devTools.getDomains().events().addConsoleListener(
                log -> System.out.println(
                        log.getTimestamp() + " " + log.getType() + " " + log.getMessages()));
        driver.get("https://fakestore.testelka.pl/console-log-events");

    }
}
