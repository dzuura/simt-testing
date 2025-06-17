package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.IncomingStockPage;

public class AddStockStepDef {
    private TestContext context = new TestContext();
    private IncomingStockPage incomingStockPage;

    @Before
    public void setup() {
        incomingStockPage = new IncomingStockPage(context.getDriver());
    }

    @Given("User navigates to the incoming stock page")
    public void userNavigatesToTheIncomingStockPage() {
        incomingStockPage.clickBarangMasukNavbar();
        String expectedUrl = "https://sim-toko.madanateknologi.web.id/barang-masuk";
        Assertions.assertEquals(expectedUrl, context.getDriver().getCurrentUrl());
    }

    @When("User clicks the {string} button")
    public void userClicksButton(String buttonLabel) throws InterruptedException {
        if (buttonLabel.equalsIgnoreCase("Tambah Barang Masuk")) {
            incomingStockPage.clickTambahBarangBtn();
            Thread.sleep(100);
        } else if (buttonLabel.equalsIgnoreCase("Tambahkan Barang Masuk")) {
            incomingStockPage.submitForm();
        }
    }

    @When("User adds item with category {string}, brand {string}, supplier {string}, ring {int}, hole {int}, width {int}, stock {int}, modal price {int}, and sale price {int}")
    public void user_adds_item_with_category_brand_supplier_ring_hole_width_stock_modal_price_and_sale_price(String kategori, String merk, String pemasok, int ring, int lebar, int lubang, int stock, int hargaModal, int hargaJual) throws InterruptedException {
        incomingStockPage.selectKategori(kategori);
        Thread.sleep(100);
        incomingStockPage.selectMerk(merk);
        incomingStockPage.selectPemasok(pemasok);
        incomingStockPage.fillDetailBarang(ring, lubang, lebar, stock);
        incomingStockPage.fillHargaBarang(hargaModal, hargaJual);
    }

    @Then("User should see add new product error message {string}")
    public void userShouldSeeAddNewProductErrorMessage(String expectedMessage) {
        String actualMessage = incomingStockPage.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Then("User should see success add stock message {string}")
    public void userShouldSeeSuccessAddStockMessage(String message) {
        String actualMessage = incomingStockPage.getMessage();
        Assertions.assertTrue(actualMessage.contains(message));
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}
