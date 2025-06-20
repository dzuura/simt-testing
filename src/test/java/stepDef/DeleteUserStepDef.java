package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PelangganPage;

public class DeleteUserStepDef {
    private TestContext context = new TestContext();
    private PelangganPage pelangganPage;
    private BasePage basePage;

    @Before
    public void setup() {
        pelangganPage = new PelangganPage(context.getDriver());
    }

    @Given("User navigates to the pelanggan page")
    public void userNavigatesToPelangganPage() throws InterruptedException{
        Thread.sleep(500);
        pelangganPage.navigateToPelangganPage();
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/pelanggan";
        Thread.sleep(500);
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
        Thread.sleep(500);
    }

    @When("User clicks the delete button for data with ID {string}")
    public void userClicksDeleteButton(String id) throws InterruptedException{
        Thread.sleep(500);
        pelangganPage.clickDeleteButton(id);
    }

    @Then("User should see the delete confirmation popup with title {string}")
    public void userShouldSeeDeleteConfirmationPopup(String expectedTitle) throws InterruptedException{
        Thread.sleep(2000);
        String actualTitle = pelangganPage.getDeletePopupTitle();
        Thread.sleep(1000);
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @When("User clicks the {string} button in the confirmation popup")
    public void userClicksConfirmationPopupButton(String buttonLabel) {
        pelangganPage.clickConfirmationPopupButton(buttonLabel);
    }

    @Then("Data pelanggan with ID {string} should be deleted")
    public void dataPelangganShouldBeDeleted(String id) throws InterruptedException{
        Thread.sleep(500);
        Assertions.assertFalse(pelangganPage.isDataPresent(id));
    }

    @Then("User should see the success delete notification with message {string}")
    public void userShouldSeeSuccessDeleteNotification(String expectedMessage) {
        String actualMessage = pelangganPage.getSuccessAlertTitle();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}
