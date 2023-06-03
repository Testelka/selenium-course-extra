import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

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
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        By quantity = By.cssSelector("input[name='quantity']");
        By addToCart = RelativeLocator.with(By.cssSelector("button")).toRightOf(quantity);
        driver.findElement(addToCart).click();
    }
}