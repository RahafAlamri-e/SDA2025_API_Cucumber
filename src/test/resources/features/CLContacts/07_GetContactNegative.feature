@CLCrud
Feature: Negative Assert

  Scenario: Get Contact
    When send the get request to get contact by id
    Then status code should be 404
    And response body should be empty