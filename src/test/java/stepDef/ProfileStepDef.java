package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.ProfilePage;

public class ProfileStepDef {
    private TestContext context = new TestContext();
    private ProfilePage userProfile;
    private BasePage basePage;

    @Before
    public void setup() {
        userProfile = new ProfilePage(context.getDriver());
    }

    @Then("User should be redirected to dashboardd")
    public void userShouldBeRedirectedToDashboardd() throws InterruptedException {
        String expectedUrl = "http://127.0.0.1:8000/dashboard";
        Thread.sleep(1000);
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @Given("User navigates to the user profile")
    public void userNavigatesToTheProfilePage() {
        userProfile.navigateToProfile();
        String expectedUrl = "http://127.0.0.1:8000/profile";
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @When("User opens the edit user modal")
    public void userOpensTheEditUserModal() throws InterruptedException{
        Thread.sleep(500);
        userProfile.openEditUserModal();
    }

    @When("User fills the add user form with name {string}, email {string}, and contact {string}")
    public void userFillsTheAddUserForm(String name, String email, String contact){
        userProfile.fillUserForm(name, email, contact);
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}