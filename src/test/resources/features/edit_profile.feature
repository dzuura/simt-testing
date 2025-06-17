Feature: Manage User Profile
  As an admin
  I want to manage user profiles
  So that I can add or edit users in the system

  Background:
    Given User is on the login page
    When User logs in with email "pelayan@gmail.com" and password "password123"

  Scenario Outline: Successfully adding a new user
    Given User navigates to the user profile
    When User opens the edit user modal
    And User fills the add user form with name "<name>", email "<email>", and contact "<contact>"
    And User clicks the submit button
    Then User should see the success notification with message "User added successfully"

    Examples:
      | name            | email               | contact        |
      | Fayyadh        | pelayan@gmail.com | 1234567890     |


    Scenario Outline: Failed user addition due to invalid input
      Given User navigates to the user profile
      When User opens the edit user modal
      And User fills the add user form with name "<name>", email "<email>", and contact "<contact>"
      And User clicks the submit button
      Then User should see the failed notification with message "Oops..."

      Examples:
        | name     | email                 | contact  |
        | fayyadh | invalid_email   | 12345    |
        | Test User|pelayan@gmail.com|    |
        ||pelayan@gmail.com| 123445   |