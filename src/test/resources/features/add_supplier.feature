Feature: Add Supplier
  As an admin
  I want to add a new supplier
  So that I can manage supplier data in the system

  Background:
    Given User is on the login page
    When User logs in with email "admin@gmail.com" and password "admin1234"
    Then User should be redirected to dashboard

  Scenario Outline: Successfully add a new supplier with valid data
    #langsung mulai dari sini
    Given User navigates to the supplier management page
    When User clicks the "Add Supplier" button
    When User fills the add supplier form with name "<name>", contact "<contact>", address "<address>", company "<company>", and category "<category>"
    And User clicks the "Add Supplier" button to submit
    Then Supplier "<name>" should appear in the supplier list

    Examples:
      | name              | contact      | address              | company | category |
      | Aruma Jaya        | 081234567890 | Jl. Merdeka No. 10   | Makmur  | Velg     |
      | CV Jaya Abadi     | 089912345678 | Jl. Sudirman No. 20  | Jaya    | Ban      |
      | CV Armada         | 081212341234 | Jl. Veteran No. 5    | Auto    | Aksesoris      |

  Scenario Outline: Failed to add supplier when name field is empty
    Given User navigates to the supplier management page
    When User clicks the "Add Supplier" button
    When User fills the add supplier form with name "<name>", contact "<contact>", address "<address>", company "<company>", and category "<category>"
    And User clicks the "Add Supplier" button to submit
    Then User should see a supplier error message "Harap isi semua field yang wajib!"

    Examples:
      | name | contact      | address           | company   | category | errorMessage                  |
      |      | 082112345678 | Jl. Melati No. 12 | IndoApril | Ban      | Supplier name is required.   |
