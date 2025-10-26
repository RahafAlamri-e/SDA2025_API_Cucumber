@GetContact @CLCrud
Feature: Get Contact By Id Feature

  Scenario: Get Contact By Id
    When send the get request to get contact by id
    Then status code should be 200
    And response body should contain corresponding contact