Feature: Registration Form Details

  Scenario: Signup button remains disabled until all mandatory fields are filled
    Then the "Sign up" button should be disabled
    When the user enters "Rahul Sharma" in the "Name" field
    And the user enters "rahul.sharma@example.com" in the "Email" field
    And the user enters "Pass@123" in the "Password" field
    And the user selects "Maharashtra" from the "State" dropdown
    And the user checks the interest "JAVA"
    And the user selects "Reading" and "Swimming" from the "Hobbies" dropdown
    And the user scrolls down the page
    Then the "Sign up" button should be enabled
    And the user clicks the "Sign up" button
    

  Scenario: Male is selected as the default gender
    Then the "Male" gender radio button should be selected by default

  Scenario: User selects interests and hobbies
    When the user checks the interest "JAVA"
    And the user scrolls down the page
    And the user selects "Reading" and "Swimming" from the "Hobbies" dropdown
    Then the interest "JAVA" should be checked
    And the hobbies "Reading" and "Swimming" should be selected