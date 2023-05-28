import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;


public class ExampleTests extends BaseTest{
    @Test
    public void test() {
        driver.get("http://localhost:3000/");
        WebElement button = driver.findElement(By.cssSelector("a.bg-primary-500"));
        Assertions.assertEquals("Cześć!", button.getText());
    }
}
