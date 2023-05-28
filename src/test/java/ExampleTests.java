import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        driver.get("http://localhost:8080/");
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        Dimension size = driver.manage().window().getSize();
        driver.manage().window().setSize(new Dimension(1024, 768));
        Dimension size2 = driver.manage().window().getSize();

        int x = driver.manage().window().getPosition().getX();
        int y = driver.manage().window().getPosition().getY();
        Point position = driver.manage().window().getPosition();
        driver.manage().window().setPosition(new Point(-1800, -200));
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();
    }
}
