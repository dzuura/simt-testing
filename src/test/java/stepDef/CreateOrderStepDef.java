package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.KatalogPage;
import pages.BasePage;

public class CreateOrderStepDef {
    private TestContext context = new TestContext();
    private KatalogPage katalogPage;
    private BasePage basePage;

    @Before
    public void setup() {
        katalogPage = new KatalogPage(context.getDriver());
        basePage = new BasePage(context.getDriver());
    }

    @When("User selects the customer named {string}")
    public void userSelectsTheCustomerNamed(String customerName) throws InterruptedException {
        katalogPage.selectCustomer(customerName);
        Thread.sleep(1000);
    }

    @When("User adds product with ID {string} to the cart")
    public void userAddsProductToTheCart(String productId) throws InterruptedException{
        katalogPage.addProductToCart(productId);
        Thread.sleep(1000);
    }


    @Then("User should see the product detail popup with title {string}")
    public void userShouldSeeTheProductDetailPopupWithTitle(String expectedTitle) throws InterruptedException{
        String modalTitle = context.getDriver().findElement(By.id("modalDetail")).getText();
        Assertions.assertTrue(modalTitle.contains(expectedTitle), "Popup title is incorrect!");
        Thread.sleep(500);
    }

    @When("User clicks the {string} button to add the product to cart")
    public void userClicksTheAddToCartButtonFromTheProductDetailModal(String buttonName) {
        katalogPage.addToCartFromModal();
    }

    @Then("User should see the success notification with message {string}")
    public void userShouldSeeTheSuccessNotificationWithMessage(String expectedMessage) throws InterruptedException{
        Thread.sleep(500);
        By successPopupLocator = By.cssSelector(".swal2-popup");
        boolean isSuccessPopupVisible = basePage.isElementPresent(successPopupLocator);
        Assertions.assertTrue(isSuccessPopupVisible, "Pesanan berhasil dibuat!");
    }

    @Then("User should see the failed notification with message {string}")
    public void userShouldSeeTheFailedNotificationWithMessage(String expectedMessage) throws InterruptedException{
        Thread.sleep(500);
        By successPopupLocator = By.cssSelector(".swal2-popup");
        boolean isSuccessPopupVisible = basePage.isElementPresent(successPopupLocator);
        Assertions.assertTrue(isSuccessPopupVisible, "Pesanan gagal dibuat!");
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}