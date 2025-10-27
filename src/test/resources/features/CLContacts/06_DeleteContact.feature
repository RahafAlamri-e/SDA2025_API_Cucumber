@CLCrud
Feature: Delete Contact Feature

  Scenario: Delete Contact
    When send the delete request to delete contact
    Then status code should be 200
    And response body should contain "Contact deleted"