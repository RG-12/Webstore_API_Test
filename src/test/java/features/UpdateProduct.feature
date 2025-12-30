Feature: Update products using PUT API

  Scenario Outline: Validate if the put product API status code works correctly
    Given I hit the url of put product api endpoint
    When I pass the url of products in the request with <ProductNumber>
    Then I receive the response code as 200
    Examples:
      | ProductNumber |
      | 6             |