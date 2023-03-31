import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;
    protected final String baseURL = "http://localhost:8080/";
    @BeforeEach
    public void setupDriver() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
