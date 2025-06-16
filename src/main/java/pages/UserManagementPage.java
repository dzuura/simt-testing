package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class UserManagementPage extends BasePage {
    private final By userManagementLink = By.cssSelector("a[href='https://sim-toko.madanateknologi.web.id/manajemen-user']");
    private final By addUserModalButton = By.cssSelector("button.btn.btn-secondary.btn-primary");
    private final By nameField = By.id("modalTambahNama");
    private final By emailField = By.id("modalTambahEmail");
    private final By passwordField = By.id("modalTambahPassword");
    private final By confirmPasswordField = By.id("modalTambahKonfirmasiPassword");
    private final By contactField = By.id("modalTambahKontak");
    private final By roleAdmin = By.id("tambahAdmin");
    private final By roleKasir = By.id("tambahKasir");
    private final By rolePelayan = By.id("tambahPelayan");
    private final By submitButton = By.cssSelector("button.btn.btn-primary[type='submit']");
    private final By sweetAlertPopup = By.cssSelector(".swal2-popup");
    private final By sweetAlertErrorMessage = By.cssSelector(".swal2-html-container");

    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToUserManagement() {
        click(userManagementLink);
    }

    public void openAddUserModal() {
        click(addUserModalButton);
    }

    public void fillUserForm(String name, String email, String password, String confirmPassword, String contact, String role) {
        sendKeys(nameField, name);
        sendKeys(emailField, email);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, confirmPassword);
        sendKeys(contactField, contact);
        if ("admin".equalsIgnoreCase(role)) {
            click(roleAdmin);
        } else if ("kasir".equalsIgnoreCase(role)) {
            click(roleKasir);
        } else if ("pelayan".equalsIgnoreCase(role)) {
            click(rolePelayan);
        }
    }

    public void submitForm() {
        click(submitButton);
    }

    public boolean isUserAdded(String name) {
        By userRow = By.xpath("//table[contains(@class, 'datatables-manajemen-user')]//td[text()='" + name + "']");
        try {
            waitForVisibility(userRow);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlertPopup));
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlertErrorMessage));
            String message = messageElement.getText().trim();

            wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(popup));
            return message;
        } catch (Exception e) {
            return "No error message found: " + e.getMessage();
        }
    }
}