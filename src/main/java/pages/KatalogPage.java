package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KatalogPage extends BasePage {
    private final By pelangganDropdown = By.cssSelector("#select2-pelanggan_select-container");
    private final By modalDetail = By.id("modalDetail");
    private final By btnAddToCart = By.cssSelector(".btn-add");

    public KatalogPage(WebDriver driver) {
        super(driver);
    }

    public void selectCustomer(String customerName) {
        click(pelangganDropdown);
        By customerOption = By.xpath("//li[text()='" + customerName + "']");
        waitForVisibility(customerOption);
        click(customerOption);
    }

    public void addToCartFromModal() {
        By addButton = By.id("btnLanjutKonfirmasi");  // Tombol untuk memasukkan keranjang
        click(addButton);
        try {
            Thread.sleep(1000);  // Sleep for 1 second to simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addProductToCart(String productId) {
        By addButton = By.cssSelector(".btn-add[data-id='" + productId + "']");
        click(addButton);
        try {
            Thread.sleep(1000);  // Sleep for 1 second to simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
