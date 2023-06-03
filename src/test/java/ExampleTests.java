import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.net.URI;
import java.util.function.Predicate;

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
        ((HasAuthentication)driver).register(UsernameAndPassword.of("harrypotter", "Alohomora"));
        driver.get("https://fakestore.testelka.pl/wp-content/uploads/protected/cos.html");
    }
}