package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerPage extends BasePage {
    private final By pelangganNavbar = By.xpath("//*[@id=\"layout-menu\"]/ul/li[4]/a");
    private final By tambahPelangganBtn = By.xpath("//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/div[2]/div/div[2]/button[1]");
    private final By namaInput = By.id("modalEditNama");
    private final By kontakInput = By.id("modalEditKontak");
    private final By alamatInput = By.id("modalEditAlamat");
    private final By submitBtn = By.xpath("//*[@id=\"tambahPelangganForm\"]/div[4]/button");
    private final By searchField = By.xpath("//*[@id=\"DataTables_Table_0_filter\"]/label/input");

    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    public void clickPelangganNavbar() {
        click(pelangganNavbar);
    }

    public void clickTambahPelangganBtn() {
        click(tambahPelangganBtn);
    }

    public void fillCustomerForm(String nama, String kontak, String alamat) {
        sendKeys(namaInput, nama);
        sendKeys(kontakInput, kontak);
        sendKeys(alamatInput, alamat);
    }

    public void submitTambahPelanggan() {
        click(submitBtn);
    }

    public boolean isPelangganAdded(String name) {
        sendKeys(searchField, name);

        By resultRow = By.xpath("//table[@id='DataTables_Table_0']//td//span[contains(text(), '" + name + "')]");
        try {
            waitForVisibility(resultRow);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
