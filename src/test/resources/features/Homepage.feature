Feature: Navigate to Registration Page

  Scenario: User clicks on New User / Signup link from the login page
    Given the user is on the login page
    When the user clicks on the "New user? Signup" link
    Then the user should be navigated to the registration page