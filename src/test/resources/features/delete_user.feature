Feature: Delete Customer Data
  As an admin
  I want to delete customer data from the system
  So that I can manage the customer records effectively

  Background:
    Given User is on the login page
    When User logs in with email "pelayan@gmail.com" and password "password123"

  Scenario Outline: Successfully deleting a customer data from the list
    Given User navigates to the pelanggan page
    When User clicks the delete button for data with ID "<customerId>"
    Then User should see the delete confirmation popup with title "Kamu yakin ingin menghapus baris data ini 🗑️?"
    When User clicks the "Hapus" button in the confirmation popup
    Then Data pelanggan with ID "<customerId>" should be deleted
    Then User should see the success delete notification with message "Terhapus!"

    Examples:
      | customerId   |
      | 28           |