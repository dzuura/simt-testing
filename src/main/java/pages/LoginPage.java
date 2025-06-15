package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends BasePage {
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button.btn.btn-warning");

    private final By emailError = By.cssSelector("[data-field='email'][data-validator='notEmpty']");
    private final By passwordError = By.cssSelector("[data-field='password'][data-validator='notEmpty']");
    private final By formatError = By.className("text-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public String getErrorMessage() {
        if (isElementPresent(emailError)) {
            return waitForVisibility(emailError).getText();
        }
        if (isElementPresent(passwordError)) {
            return waitForVisibility(passwordError).getText();
        }
        if (isElementPresent(formatError)) {
            return waitForVisibility(formatError).getText();
        }
        return "Unknown error";
    }
}
