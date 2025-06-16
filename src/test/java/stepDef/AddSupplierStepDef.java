package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.SupplierPage;

public class AddSupplierStepDef {
    private TestContext context = new TestContext();
    private SupplierPage supplierPage;

    @Before
    public void setup() {
        supplierPage = new SupplierPage(context.getDriver());
    }

    @Given("User navigates to the supplier management page")
    public void userNavigatesToTheSupplierManagementPage() {
        context.getDriver().get("https://sim-toko.madanateknologi.web.id/master/pemasok");
        Assertions.assertEquals("https://sim-toko.madanateknologi.web.id/master/pemasok", context.getDriver().getCurrentUrl());
    }

    @When("User clicks the {string} button")
    public void userClicksTheButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add Supplier")) {
            supplierPage.openAddSupplierModal();
        }
    }

    @When("User fills the add supplier form with name {string}, contact {string}, address {string}, company {string}, and category {string}")
    public void userFillsTheAddSupplierForm(String name, String contact, String address, String company, String category) {
        supplierPage.fillSupplierForm(name, contact, address, company, category);
    }


    @When("User clicks the {string} button to submit")
    public void userClicksTheButtonToSubmit(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add Supplier")) {
            supplierPage.submitForm();
        }
    }

    @Then("Supplier {string} should appear in the supplier list")
    public void supplierShouldAppearInTheSupplierList(String name) {
        Assertions.assertTrue(supplierPage.isSupplierAdded(name));
    }

    @Then("User should see a supplier error message {string}")
    public void userShouldSeeAnErrorMessage(String expectedMessage) {
        String actualMessage = supplierPage.getErrorMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}
