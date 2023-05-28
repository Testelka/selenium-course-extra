import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    public void single_choice_select_example() {
        driver.get("https://fakestore.testelka.pl/lista-rozwijana/");
        WebElement selectElement = driver.findElement(By.cssSelector("select#flavors"));

        Select select = new Select(selectElement);
        //select.selectByIndex(3);
        //select.selectByVisibleText("marakuja");
        //select.selectByValue("passion-fruit");

        Assertions.assertEquals(4, select.getOptions().size());
    }

    @Test
    public void multiple_choice_select_example() {
        driver.get("https://fakestore.testelka.pl/lista-rozwijana/");
        WebElement selectElement = driver.findElement(By.cssSelector("select#flavors-multiple"));
        Select select = new Select(selectElement);

        Assertions.assertTrue(select.isMultiple());
    }

    @Test
    public void multiple_choice_select_with_selected_options_example() {
        driver.get("https://fakestore.testelka.pl/lista-rozwijana/");
        WebElement selectElement = driver.findElement(By.cssSelector("select#flavors-multiple-selected"));
        Select select = new Select(selectElement);

        //select.deselectAll();
        //select.deselectByIndex(1);
        //select.deselectByValue("chocolate");
        //select.deselectByVisibleText("czekoladowy");

        Assertions.assertEquals(2, select.getAllSelectedOptions().size());

    }

}
