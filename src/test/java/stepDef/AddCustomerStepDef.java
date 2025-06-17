package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.CustomerPage;

public class AddCustomerStepDef {
    private TestContext context = new TestContext();
    private CustomerPage customerPage;

    @Before
    public void setup() {
        customerPage = new CustomerPage(context.getDriver());
    }

    @Then("User should be redirected to katalog page")
    public void userShouldBeRedirectedToKatalogPage() throws InterruptedException {
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/katalog";
        Thread.sleep(1000);
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @Given("User navigates to the customer page")
    public void userNavigatesToTheCustomerPage() {
        customerPage.clickPelangganNavbar();
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/pelanggan";
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @When("User clicks the Daftar Pelanggan button")
    public void userClicksTheDaftarPelangganButton() {
        customerPage.clickTambahPelangganBtn();
    }

    @And("User adds a customer with name {string}, contact {string}, and address {string}")
    public void userAddsACustomerWithNameContactAndAddress(String name, String contact, String address) {
        customerPage.fillCustomerForm(name, contact, address);
    }

    @And("User clicks the Tambahkan Pelanggan Baru button")
    public void userClicksTheTambahkanPelangganBaruButton() {
        customerPage.submitTambahPelanggan();
    }

    @Then("User should see the new customer {string} in the customer table")
    public void userShouldSeeTheNewCustomerInTheCustomerTable(String name) throws InterruptedException {
        Thread.sleep(1000);
        Assertions.assertTrue(customerPage.isPelangganAdded(name));
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}
