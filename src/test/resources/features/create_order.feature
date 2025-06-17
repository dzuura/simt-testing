Feature: Create Order
  As a customer
  I want to add products to my cart and place an order
  So that I can purchase products from the catalog

  Background:
    Given User is on the login page
    When User logs in with email "pelayan@gmail.com" and password "password123"

  Scenario Outline: Successfully adding a product to the cart and creating an order
    Given User selects the customer named "<customerName>"
    When User adds product with ID "<productId>" to the cart
    Then User should see the product detail popup with title "Detail Produk"
    When User clicks the "Masukkan Keranjang" button to add the product to cart
    Then User should see the success notification with message "Pesanan berhasil dibuat!"

    Examples:
      | customerName      | productId   |
      | Rowland Pagac     | JAS00001    |
      | Eldridge Volkman     | JAS00002    |

  Scenario Outline: Failed order creation due to invalid customer or product
    When User adds product with ID "<productId>" to the cart
    And User should see the product detail popup with title "Detail Produk"
    When User clicks the "Masukkan Keranjang" button to add the product to cart
    Then User should see the failed notification with message "Perhatian"

    Examples:
      | productId   |
      | JAS00001    |
      | JAS00002    |
