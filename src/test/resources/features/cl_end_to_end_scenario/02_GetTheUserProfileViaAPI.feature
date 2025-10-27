@E2E
Feature: Get User Profile Feature

  Scenario: Get User Profile
    When send the get request to get user profile
    Then status code should be 200
    And response body should contain corresponding user