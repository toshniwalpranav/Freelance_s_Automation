Feature: Login Functionality

  Scenario: User logs in with valid email and password
    Given the user is on the login page
    When the user logs in with email "rahul.sharma@example.com" and password "Pass@123"
    Then the user should be navigated to another page