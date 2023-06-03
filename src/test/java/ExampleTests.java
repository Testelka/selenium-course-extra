import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import static org.openqa.selenium.remote.http.Contents.utf8String;

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
        NetworkInterceptor network = new NetworkInterceptor(driver,
                Route.matching(req -> req.getUri().contains(".css"))
                        .to(() -> req -> new HttpResponse().setStatus(200)
                                .setHeader("Content-Type", "text/css")
                                .setContent(utf8String("Nie lubię CSSa"))));
        driver.get("https://fakestore.testelka.pl");
        network.close();
    }
}