import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    @RegisterExtension
    TestStatus status = new TestStatus();
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach(TestInfo info) {
        if (status.isFailed) {
            takeScreenshot(info.getDisplayName());
        }
        driver.quit();
    }

    private void takeScreenshot(String testName) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String folderLocation = "./screenshots/";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String formattedNow = LocalDateTime.now().format(formatter);

        try {
            Files.createDirectories(Paths.get(folderLocation));
            Path destinationPath = Paths.get(folderLocation + testName + formattedNow + ".png");
            Files.copy(screenshot.toPath(), destinationPath);
            System.out.println("Screenshot saved at " + destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
