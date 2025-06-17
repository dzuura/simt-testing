package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends BasePage {
    private final By openEditUserButton = By.cssSelector("a.btn.btn-primary");
    private final By nameField = By.id("nama");
    private final By emailField = By.id("email");
    private final By contactField = By.id("kontak");
    private final By submitButton = By.cssSelector("button.btn.btn-primary[type='submit']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void openEditUserModal() {
        click(openEditUserButton);
    }

    public void navigateToProfile() {
        click(By.cssSelector(".nav-link.dropdown-toggle.hide-arrow"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(By.cssSelector(".dropdown-item.waves-effect[href='https://sim-toko.madanateknologi.web.id/profile']"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fillUserForm(String name, String email, String contact) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);

        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        driver.findElement(contactField).clear();
        driver.findElement(contactField).sendKeys(contact);
    }

    public void submitForm() {
        click(submitButton);
    }

    public String getSuccessAlertTitleProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".swal2-title")
        ));
        return title.getText();
    }
}