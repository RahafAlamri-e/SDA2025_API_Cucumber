@CLCrud @GetDeleteContact
Feature: Get Delete Contact Feature

  Scenario: Get Delete Contact
    When send the get request to get contact by id
    Then status code should be 404
    Then response body should return empty