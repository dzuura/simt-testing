package stepDef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestContext {
    private static WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get("https://sim-toko.madanateknologi.web.id/login");
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}