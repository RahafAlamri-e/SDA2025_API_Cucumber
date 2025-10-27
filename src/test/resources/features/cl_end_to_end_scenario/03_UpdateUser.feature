@E2E
Feature: Update User Feature

  Scenario: Update User
    Given prepare payload named "user_body"
    When send patch request to update the user
    Then status code should be 200
    And response body should contain corresponding user