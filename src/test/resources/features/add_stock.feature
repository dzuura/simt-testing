Feature: Add New Stock
  As a kasir
  I want to add new stock data
  So that the system can manage addition stock

  Background:
    Given User is on the login page
    When User logs in with email "kasir@gmail.com" and password "password123"
    Then User should be redirected to katalog page

  Scenario Outline: Successfully add new stock with valid data
    Given User navigates to the incoming stock page
    When User clicks the "Tambah Barang Masuk" button
    And User adds item with category "<kategori>", brand "<merk>", supplier "<pemasok>", ring <ring>, hole <lubang>, width <lebar>, stock <stok>, modal price <hargaModal>, and sale price <hargaJual>
    And User clicks the "Tambahkan Barang Masuk" button
    Then User should see success add stock message "Barang masuk berhasil ditambahkan"

    Examples:
      | kategori | merk       | pemasok               | ring | lubang | lebar | stok | hargaModal | hargaJual |
      | Velg     | Baseus     | Alta Walsh            | 15   | 5      | 20    | 10   | 50000      | 75000     |

  Scenario: Failed to add new product because no category was selected
    Given User navigates to the incoming stock page
    When User clicks the "Tambah Barang Masuk" button
    And User clicks the "Tambahkan Barang Masuk" button
    Then User should see add new product error message "The kategori id field is required"

  Scenario Outline: Failed to add new product because stock quantity is zero
    Given User navigates to the incoming stock page
    When User clicks the "Tambah Barang Masuk" button
    And User adds item with category "<kategori>", brand "<merk>", supplier "<pemasok>", ring <ring>, hole <lubang>, width <lebar>, stock <stok>, modal price <hargaModal>, and sale price <hargaJual>
    And User clicks the "Tambahkan Barang Masuk" button
    Then User should see add new product error message "<errorMessage>"

    Examples:
      | kategori | merk       | pemasok               | ring | lubang | lebar | stok | hargaModal | hargaJual | errorMessage                              |
      | Velg     | OZ Racing  | Johathan Jacobs MD    | 17   | 5      | 20    | 0    | 30000      | 45000     | The jumlah masuk field must be at least 1 |
