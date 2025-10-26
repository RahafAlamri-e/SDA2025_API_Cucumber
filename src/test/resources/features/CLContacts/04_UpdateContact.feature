@CLCrud @UpdateContact
Feature: Update Contact Feature

  Scenario: Update Contact
    Given prepare payload named "contact_body_updated"
    When send put request to update contact
    Then status code should be 200
    Then response body should contain corresponding contact