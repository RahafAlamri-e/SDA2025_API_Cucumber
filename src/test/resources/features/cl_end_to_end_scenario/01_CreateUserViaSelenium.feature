@E2E
Feature: Create User Feature

  Scenario: Create User
    Given user goes to url "https://thinking-tester-contact-list.herokuapp.com/"
    When user clicks on sign up button
    And user fills the form and submit
    Then close browser

