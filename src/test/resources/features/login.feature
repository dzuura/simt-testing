Feature: Login
  As a user
  I want to log in to the system
  So that I can access the application

  Scenario Outline: Successful login with valid credentials
    Given User is on the login page
    When User logs in with email "<email>" and password "<password>"
    Then User should be redirected to dashboard

    Examples:
      | email            | password   |
      | admin@gmail.com  | admin1234  |

  Scenario Outline: Failed login with invalid credentials
    Given User is on the login page
    When User logs in with email "<email>" and password "<password>"
    Then User should see error message "<errorMessage>"

    Examples:
      | email             | password    | errorMessage              |
      |                   | 1234        | Masukkan email Anda       |
      | admin@gmail.com   |             | Masukkan password Anda    |
      | admin1            | 1234        | Format email tidak valid. |
      | admin@gmail.com   | 12345       | Email atau password salah |
      | test@gmail.com    | admin1234   | Email atau password salah |
      | test@gmail.com    | 1234        | Email atau password salah |