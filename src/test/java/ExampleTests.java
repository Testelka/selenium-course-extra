import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExampleTests {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
    @Test
    public void drag_box_to_another_box_should_show_that_dropped() {
        driver.navigate().to("https://fakestore.testelka.pl/actions");
        WebElement draggable = driver.findElement(By.cssSelector("#draggable"));
        WebElement droppable = driver.findElement(By.cssSelector("#droppable"));
        actions.dragAndDrop(draggable, droppable).perform();
        Assertions.assertEquals("Dropped!", droppable.getText(),
                "Message in the droppable box was not changed. Was the element dropped?");
    }

    @Test
    public void drag_box_to_another_box_corner_should_show_that_dropped() {
        driver.navigate().to("https://fakestore.testelka.pl/actions");
        WebElement draggable = driver.findElement(By.cssSelector("#draggable"));
        WebElement droppable = driver.findElement(By.cssSelector("#droppable"));
        actions.clickAndHold(draggable).moveToElement(droppable, 74, 74).release().perform();
        Assertions.assertEquals("Dropped!", droppable.getText(),
                "Message in the droppable box was not changed. Was the element dropped?");
    }
}