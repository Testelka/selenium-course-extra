import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.webaudio.model.AudioListenerWillBeDestroyed;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

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
        driver.navigate().to("https://fakestore.testelka.pl/select/");
        List<WebElement> items = driver.findElements(By.cssSelector(".ui-selectee"));
        Actions actions = new Actions(driver);
        actions
                .keyDown(Keys.CONTROL)
                .click(items.get(1))
                .click(items.get(3))
                .click(items.get(4))
                .keyUp(Keys.CONTROL)
                .perform();

        List<WebElement> selectedItems = driver.findElements(By.cssSelector(".ui-selected"));
        Assertions.assertEquals(3, selectedItems.size(),
                "Number of selected items is not what expected.");

    }
}