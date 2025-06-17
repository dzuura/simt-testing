package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PelangganPage {
    private WebDriver driver;

    public PelangganPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigasi ke halaman pelanggan
    public void navigateToPelangganPage() {
        driver.get("https://sim-toko.madanateknologi.web.id/pelanggan");
    }

    // Klik tombol hapus berdasarkan ID
    public void clickDeleteButton(String id) throws InterruptedException {
        Thread.sleep(500);
        WebElement deleteButton = driver.findElement(By.cssSelector("a.hapus-data[data-id='" + id + "']"));
        deleteButton.click();
    }

    // Ambil judul popup konfirmasi
    public String getDeletePopupTitle() {
        WebElement popupTitle = driver.findElement(By.cssSelector(".swal2-html-container .rounded-top h4"));
        return popupTitle.getText();
    }

    // Klik tombol Hapus pada popup konfirmasi
    public void clickConfirmationPopupButton(String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space(text())='" + label + "']")
        ));
        button.click();
    }

    // Verifikasi bahwa data pelanggan tidak ada
    public boolean isDataPresent(String id) {
        try {
            driver.findElement(By.cssSelector("tr[data-id='" + id + "']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Ambil ID user berdasarkan nama yang baru saja ditambahkan
    public String getUserIdByName(String name) {
        WebElement userRow = driver.findElement(By.xpath("//table[contains(@class, 'datatables-master-pelanggan')]//td[text()='" + name + "']/.."));
        return userRow.getAttribute("data-id");
    }

    // Ambil judul notifikasi sukses
    public String getSuccessAlertTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".swal2-title")
        ));
        return title.getText();
    }
}
