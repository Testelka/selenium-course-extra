import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    public void screenshotExample() {
        driver.get("http://localhost:3000/");
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("./target/image.png");
        try {
            Files.copy(screenshot.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void elementScreenshotExample() {
        driver.get("http://localhost:3000/");
        WebElement element = driver.findElement(By.cssSelector(".items-end.mx-auto"));
        File screenshot = element.getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("./target/image.png");
        try {
            Files.copy(screenshot.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
