import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.HasLogEvents;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

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
        driver.get("https://fakestore.testelka.pl/zmiany-w-dom/");
        AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);

        ((HasLogEvents) driver).onLogEvent(domMutation(mutation -> {
            seen.set(mutation);
            latch.countDown();
        }));
        WebElement secondButton = driver.findElement(
                By.cssSelector("#second-button"));
        secondButton.click();
        Assertions.assertAll(
                () -> Assertions.assertTrue(
                        latch.await(10, TimeUnit.SECONDS)),
                () -> Assertions.assertEquals(
                        "class" , seen.get().getAttributeName()),
                () -> Assertions.assertEquals(
                        "primary-button",
                        seen.get().getCurrentValue())
        );
    }
}
