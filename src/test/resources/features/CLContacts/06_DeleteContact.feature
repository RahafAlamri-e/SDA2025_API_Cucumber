@CLCrud @DeleteContact
Feature: Delete Contact Feature

  Scenario: Delete Contact
    When send delete request to delete created contact
    Then status code should be 200
    Then response body should be as "Contact deleted"