import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void test() {
        driver.get("http://localhost:8080/" +
                "product/calculus-made-easy-by-silvanus-p-thompson/");
        driver.findElement(By.cssSelector("[name=add-to-cart]")).click();

        int size = driver.manage().getCookies().size();
        Cookie itemsInCartCookie = driver.manage().getCookieNamed("woocommerce_items_in_cart");
        //driver.manage().deleteCookie(itemsInCartCookie);
        driver.manage().deleteCookieNamed("woocommerce_items_in_cart");

        //driver.manage().deleteAllCookies();
        Assertions.assertEquals(2, driver.manage().getCookies().size());
    }
    @Test
    public void test2() {
        driver.get("https://fakestore.testelka.pl/product/windsurfing-w-lanzarote-costa-teguise/");
        driver.findElement(By.cssSelector("[name=add-to-cart]")).click();
        Cookie newCookie = new Cookie("test cookie name",
                "test cookie value",
                "fakestore.testelka.pl",
                "/",
                new GregorianCalendar(2023, Calendar.AUGUST, 4).getTime(),
                true,
                true);
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(5, driver.manage().getCookies().size());
    }
}
