import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExampleTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://fakestore.testelka.pl/alerty/");
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
    @Test
    public void alert() {
        driver.findElement(By.cssSelector("[onclick='alertFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("To jest po prostu alert", alert.getText());
        alert.accept();
    }

    @Test
    public void confirm_accept() {
        driver.findElement(By.cssSelector("[onclick='confirmFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String message = driver.findElement(By.cssSelector("#demo")).getText();
        Assertions.assertEquals("Wybrana opcja to OK!", message);
    }
    @Test
    public void confirm_dismiss() {
        driver.findElement(By.cssSelector("[onclick='confirmFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String message = driver.findElement(By.cssSelector("#demo")).getText();
        Assertions.assertEquals("Wybrana opcja to Cancel!", message);
    }
    @Test
    public void prompt() {
        driver.findElement(By.cssSelector("[onclick='promptFunction()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Anka");
        alert.accept();
        String message = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        Assertions.assertEquals("Cześć Anka! Jak leci?", message);
    }

    @Test
    public void alert_with_wait() {
        driver.findElement(By.cssSelector("[onclick='delayedAlertFunction()']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assertions.assertEquals("Miałem mały poślizg", alert.getText());
    }
}