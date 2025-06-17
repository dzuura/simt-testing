package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class IncomingStockPage extends BasePage {
    private final By barangMasukNavbar = By.xpath("//*[@id=\"layout-menu\"]/ul/li[5]/a");
    private final By tambahBarangBtn = By.xpath("//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/div[2]/div[2]/div[2]/button[1]");
    private final By kategoriDropdown = By.id("select2-modalSelectKategori-container");
    private final By merkDropdown = By.id("select2-modalSelectMerk-container");
    private final By pemasokDropdown = By.id("select2-modalSelectPemasok-container");
    private final By ringInput = By.id("modalEditRing");
    private final By lubangInput = By.id("modalEditHoleVelg");
    private final By lebarInput = By.id("modalEditLebar");
    private final By stockInput = By.id("modalEditStok");
    private final By hargaModalInput = By.id("modalEditHargaModal");
    private final By hargaJualInput = By.id("modalEditHargaJual");
    private final By tambahBarangSubmit = By.xpath("//*[@id=\"tambahBarangMasukForm\"]/div[13]/button");
    private final By sweetAlertPopup = By.cssSelector(".swal2-popup");
    private final By sweetAlertErrorMessage = By.cssSelector(".swal2-html-container");

    public IncomingStockPage(WebDriver driver) {
        super(driver);
    }

    public void clickBarangMasukNavbar() {
        click(barangMasukNavbar);
    }

    public void clickTambahBarangBtn() {
        click(tambahBarangBtn);
    }

    public void selectKategori(String kategori) {
        click(kategoriDropdown);
        By kategoriOption = By.xpath("//li[contains(text(), '" + kategori + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(kategoriOption)).click();
    }

    public void selectMerk(String merk) {
        click(merkDropdown);
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"modalTambahProduk\"]/span/span/span[1]/input")
        ));
        searchInput.sendKeys(merk);

        By merkOption = By.xpath("//li[contains(text(), '" + merk + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(merkOption)).click();
    }

    public void selectPemasok(String pemasok) {
        click(pemasokDropdown);
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"modalTambahProduk\"]/span/span/span[1]/input")
        ));
        searchInput.sendKeys(pemasok);

        By pemasokOption = By.xpath("//li[contains(text(), '" + pemasok + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(pemasokOption)).click();
    }

    public void fillDetailBarang(int ring, int lubang, int lebar, int stock) {
        sendKeys(ringInput, String.valueOf(ring));
        sendKeys(lubangInput, String.valueOf(lubang));
        sendKeys(lebarInput, String.valueOf(lebar));
        sendKeys(stockInput, String.valueOf(stock));
    }

    public void fillHargaBarang(int hargaModal, int hargaJual) {
        sendKeys(hargaModalInput, String.valueOf(hargaModal));
        sendKeys(hargaJualInput, String.valueOf(hargaJual));
    }

    public void submitForm() {
        click(tambahBarangSubmit);
    }

    public String getMessage() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlertPopup));
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlertErrorMessage));
            String message = messageElement.getText().trim();

            wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(popup));
            return message;
        } catch (Exception e) {
            return "No message found: " + e.getMessage();
        }
    }
}
