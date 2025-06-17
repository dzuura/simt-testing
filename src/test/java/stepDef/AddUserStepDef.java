package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.UserManagementPage;

public class AddUserStepDef {
    private TestContext context = new TestContext();
    private UserManagementPage userPage;

    @Before
    public void setup() {
        userPage = new UserManagementPage(context.getDriver());
    }

    @Then("User should be redirected to dashboard")
    public void userShouldBeRedirectedToDashboard() throws InterruptedException {
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/dashboard";
        Thread.sleep(1000);
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @Given("User navigates to the user management page")
    public void userNavigatesToTheUserManagementPage() {
        userPage.navigateToUserManagement();
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/manajemen-user";
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @When("User opens the add user modal")
    public void userOpensTheAddUserModal() {
        userPage.openAddUserModal();
    }

    @When("User fills the add user form with name {string}, email {string}, password {string}, confirm password {string}, contact {string}, and role {string}")
    public void userFillsTheAddUserForm(String name, String email, String password, String confirmPassword, String contact, String role) {
        userPage.fillUserForm(name, email, password, confirmPassword, contact, role);
    }

    @When("User clicks the submit button")
    public void userClicksTheSubmitButton() {
        userPage.submitForm();
    }

    @Then("User should see the new user {string} in the user list")
    public void userShouldSeeTheNewUserInTheUserList(String name) {
        Assertions.assertTrue(userPage.isUserAdded(name));
    }

    @Then("User should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String expectedMessage) {
        String actualMessage = userPage.getErrorMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}