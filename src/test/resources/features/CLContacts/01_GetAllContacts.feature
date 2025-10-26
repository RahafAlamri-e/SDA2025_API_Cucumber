@AllContacts @CLCrud
Feature: Get All Contacts Feature

  Scenario: Get All Contacts
    When send the get request to get all contacts
    Then status code should be 200
    And response body should contain contact bodies