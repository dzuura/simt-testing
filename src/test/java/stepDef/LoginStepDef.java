package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginStepDef {
    private TestContext context = new TestContext();
    private LoginPage loginPage;

    @Before
    public void setup() {
        loginPage = new LoginPage(context.getDriver());
    }

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/login";
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @When("User logs in with email {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("User should be redirected to dashboard")
    public void userShouldBeRedirectedToDashboard() {
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/dashboard";
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @Then("User should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        String message = loginPage.getErrorMessage();
        Assertions.assertNotNull(message);
        Assertions.assertEquals(expectedMessage, loginPage.getErrorMessage());
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}
