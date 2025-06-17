package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SupplierPage extends BasePage{
    private final By addButton = By.xpath("//*[@id=\"DataTables_Table_0_wrapper\"]/div[1]/div[2]/div/div[2]/button[1]");
    private final By titleModal = By.xpath("//*[@id=\"modalTambahPemasok\"]/div/div/div[1]/h1");
    private final By nameField = By.id("modalEditNama");
    private final By kontakField = By.id("modalEditKontak");
    private final By alamatField = By.id("modalEditAlamat");
    private final By perusahaanField = By.id("modalEditPerusahaan");
    private final By velgRadioButton = By.xpath("//*[@id=\"tambahPemasokForm\"]/div[5]/div/div[1]");
    private final By banRadioButton = By.xpath("//*[@id=\"tambahPemasokForm\"]/div[5]/div/div[2]");
    private final By aksesorisRadioButton = By.xpath("//*[@id=\"tambahPemasokForm\"]/div[5]/div/div[3]");
    private final By jasaRadioButton = By.xpath("//*[@id=\"tambahPemasokForm\"]/div[5]/div/div[4]");
    private final By submitButton = By.xpath("//*[@id=\"tambahPemasokForm\"]/div[6]/button");
    private final By sweetAlertPopup = By.cssSelector(".swal2-popup");
    private final By sweetAlertErrorMessage = By.cssSelector(".swal2-html-container");
    private final By modalDelete = By.xpath("//*[@id=\"swal2-html-container\"]/div/h4");
    private final By deleteButtonConfirm = By.xpath("/html/body/div[3]/div/div[6]/button[3]");
    private final By rowsSelector = By.xpath("#DataTables_Table_0 > tbody > tr:nth-child(1)");




    public SupplierPage(WebDriver driver) {
        super(driver);
    }

    public void openAddSupplierModal() {
        click(addButton);
        waitForVisibility(titleModal); // pastikan modal tampil
    }

    public void fillSupplierForm(String name, String contact, String address, String company, String category) {
        sendKeys(nameField, name);
        sendKeys(kontakField, contact);
        sendKeys(alamatField, address);
        sendKeys(perusahaanField, company);

        // Pilih kategori pemasok
        switch (category.toLowerCase()) {
            case "velg":
                click(velgRadioButton);
                break;
            case "ban":
                click(banRadioButton);
                break;
            case "aksesoris":
                click(aksesorisRadioButton);
                break;
            case "jasa":
                click(jasaRadioButton);
                break;
            default:
                throw new IllegalArgumentException("Kategori tidak dikenal: " + category);
        }
    }

    public void submitForm() {
        click(submitButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-add-supplier")));
    }

    public String getErrorMessage() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlertPopup));
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlertErrorMessage));
            String message = messageElement.getText().trim();

            // tunggu notifikasi hilang
            wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(popup));
            return message;
        } catch (Exception e) {
            return "No error message found: " + e.getMessage();
        }
    }

    public boolean isSupplierAdded(String expectedName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Pastikan tabel muncul
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//table[contains(@id, 'DataTables')]/tbody")));

            // Tunggu hingga supplier muncul dalam daftar (looping sampai timeout)
            return wait.until(driver -> {
                List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@id,'DataTables')]/tbody/tr"));
                for (WebElement row : rows) {
                    String rowText = row.getText().trim().toLowerCase();
                    if (rowText.contains(expectedName.trim().toLowerCase())) {
                        return true;
                    }
                }
                return false; // Jika tidak ditemukan, akan terus mencoba sampai timeout
            });

        } catch (TimeoutException e) {
            System.out.println("Timeout: Supplier '" + expectedName + "' tidak ditemukan dalam tabel.");
            return false;
        }
    }





    public By getDeleteButtonBySupplierName(String supplierName) {
        return By.xpath("//table[@id='DataTables_Table_0']//tr[td[text()='" + supplierName + "']]//td[last()]//a[1]");
    }

    public void deleteSupplierByName(String supplierName) {
        By deleteButton = getDeleteButtonBySupplierName(supplierName);
        waitForVisibility(deleteButton);
        click(deleteButton);
    }

    public void confirmDelete() {
        waitForVisibility(modalDelete);
        click(deleteButtonConfirm);
        wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(modalDelete));
    }

    public boolean isSupplierStillPresent(String supplierName) {
        By supplierRow = By.xpath("//table[@id='DataTables_Table_0']//td[text()='" + supplierName + "']");
        try {
            wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(supplierRow));
            return false; // Sudah terhapus
        } catch (Exception e) {
            return true; // Masih ada
        }
    }
}
