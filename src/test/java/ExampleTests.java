import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.print.PageMargin;
import org.openqa.selenium.print.PageSize;
import org.openqa.selenium.print.PrintOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

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
    public void printPageExample() {
        driver.get("http://localhost:3000/");
        PrintsPage printer = (PrintsPage) driver;
        PrintOptions printOptions = new PrintOptions();
        PageSize pageSize = new PageSize(27.94, 21.59);
        printOptions.setPageSize(pageSize);
        PageMargin pageMargin = new PageMargin(0, 0, 0, 0);
        printOptions.setPageMargin(pageMargin);
        printOptions.setBackground(true);
        printOptions.setScale(0.50);

        Pdf pdf = printer.print(printOptions);
        String content = pdf.getContent();

        Path outputPath = Paths.get("./target/output.pdf");

        byte[] decodedBytes = Base64.getDecoder().decode(content);
        try {
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while writing the PDF file: " + e);
        }
    }

}
