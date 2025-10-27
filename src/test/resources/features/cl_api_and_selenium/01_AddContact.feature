@ApiAndSelenium
Feature: Add Contact Feature

  Scenario: Create User
    Given user goes to url "https://thinking-tester-contact-list.herokuapp.com/"
    When user clicks on sign up button
    And user fills the form and submit
    Then close browser


  Scenario: Add Contact
    Given sign in via API
    When add contact via Selenium
    Then assert the contact is added
    And close browser


  Scenario: Delete created test Contact
    Given sign in via API
    When user navigates to "contactList" endpoint
    And delete created contact through api
    Then assert the contact is deleted
    And close browser

  Scenario: Delete User
    When send the delete request to delete user
    Then status code should be 200
    And response body should be empty