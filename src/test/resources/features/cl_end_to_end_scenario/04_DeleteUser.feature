@E2E
Feature: Delete User Feature

  Scenario: Delete User
    When send the delete request to delete user
    Then status code should be 200
    And response body should be empty

