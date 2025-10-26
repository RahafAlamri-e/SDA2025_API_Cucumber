@AddContact @CLCrud
Feature: Add Contact Feature

  Scenario: Add Contact Happy Path
    Given prepare payload named "contact_body"
    When send post request to create contact
    Then status code should be 201
    And response body should contain corresponding contact