Feature: Add New Customer
  As a kasir
  I want to add a new customer
  So that the system can manage additional customer

  Background:
    Given User is on the login page
    When User logs in with email "kasir@gmail.com" and password "password123"
    Then User should be redirected to katalog page

  Scenario Outline: Successfully add customer with valid data
    Given User navigates to the customer page
    When User clicks the Daftar Pelanggan button
    And User adds a customer with name "<name>", contact "<contact>", and address "<address>"
    And User clicks the Tambahkan Pelanggan Baru button
    Then User should see the new customer "<name>" in the customer table

    Examples:
      | name            | contact       | address        |
      | Test Customer 1 | 082112223333  | Sleman         |
      | Test Customer 2 | 081234567890  | Yogyakarta     |

  Scenario Outline: Failed to add customer with invalid data
    Given User navigates to the customer page
    When User clicks the Daftar Pelanggan button
    And User adds a customer with name "<name>", contact "<contact>", and address "<address>"
    And User clicks the Tambahkan Pelanggan Baru button
    Then User should see an error message "<errorMessage>"

    Examples:
      | name            | contact       | address    | errorMessage                      |
      |                 |               |            | Harap isi semua field yang wajib! |
      | Test Customer 3 |               |            | Harap isi semua field yang wajib! |
      | Test Customer 3 |               | Yogyakarta | Harap isi semua field yang wajib! |
      | Test Customer 3 | 08123456789   |            | Harap isi semua field yang wajib! |
      |                 | 08123456789   |            | Harap isi semua field yang wajib! |
      |                 | 08123456789   | Yogyakarta | Harap isi semua field yang wajib! |
      |                 |               | Yogyakarta | Harap isi semua field yang wajib! |
