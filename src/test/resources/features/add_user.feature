Feature: Add User
  As an admin
  I want to add a new user
  So that the system can manage additional staff

  Background:
    Given User is on the login page
    When User logs in with email "admin@gmail.com" and password "admin1234"
    Then User should be redirected to dashboard

  Scenario Outline: Successfully add a new user with valid data
    Given User navigates to the user management page
    When User opens the add user modal
    And User fills the add user form with name "<name>", email "<email>", password "<password>", confirm password "<confirmPassword>", contact "<contact>", and role "<role>"
    And User clicks the submit button
    Then User should see the new user "<name>" in the user list

    Examples:
      | name        | email             | password | confirmPassword | contact     | role    |
      | Test User 1 | test1@example.com | pass1234 | pass1234        | 08123456789 | admin   |
      | Test User 2 | test2@example.com | pass1234 | pass1234        | 08123456789 | kasir   |
      | Test User 3 | test3@example.com | pass1234 | pass1234        | 08987654321 | pelayan |

  Scenario Outline: Failed to add user with invalid data
    Given User navigates to the user management page
    When User opens the add user modal
    And User fills the add user form with name "<name>", email "<email>", password "<password>", confirm password "<confirmPassword>", contact "<contact>", and role "<role>"
    And User clicks the submit button
    Then User should see an error message "<errorMessage>"

    Examples:
      | name        | email             | password | confirmPassword | contact     | role  | errorMessage                                      |
      |             |                   |          |                 |             |       | Harap isi semua field!                            |
      |             | test4@example.com | pass1234 | pass1234        | 08123456789 | admin | Harap isi semua field!                            |
      | Test User 4 |                   | pass1234 | pass1234        | 08123456789 | admin | Harap isi semua field!                            |
      | Test User 4 | test4@example.com |          |                 | 08123456789 | admin | Harap isi semua field!                            |
      | Test User 4 | test4@example.com | pass1234 | pass1234        |             | admin | Harap isi semua field!                            |
      | Test User 4 | test4@example.com | pass1234 | pass1234        | 08123456789 |       | Harap isi semua field!                            |
      | Test User 4 | test4             | pass1234 | pass1234        | 08123456789 | admin | The email field must be a valid email address.    |
      | Test User 4 | test4@example.com | pass12   | pass12          | 08123456789 | admin | The password field must be at least 8 characters. |
      | Test User 4 | test4@example.com | pass1234 | pass5678        | 08123456789 | admin | Password dan konfirmasi password tidak sama!      |
      | Test User 4 | test3@example.com | pass1234 | pass1234        | 08123456789 | admin | Gunakan email lain.                               |