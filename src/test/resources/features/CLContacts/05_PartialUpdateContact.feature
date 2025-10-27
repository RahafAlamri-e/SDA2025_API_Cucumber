@CLCrud @UpdateContact
Feature: Partial Update Contact Feature

  Scenario: Partial Update Contact
    Given prepare payload named "contact_body_partialUpdated"
    When send patch request to update contact
    Then status code should be 200
    Then response body should contain updated field