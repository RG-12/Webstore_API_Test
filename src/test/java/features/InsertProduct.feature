Feature: Insert products using POST API

  Scenario Outline: Validate if the post product API status code works correctly
    Given I hit the url of post product api endpoint
    When I pass the url of products in the request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response code as 201
    Examples:
      | ProductTitle |
      | Shoes        |

  Scenario Outline: Validate if the ID of the product matches
    Given I hit the url of post product api endpoint
    When I pass the url of products in the request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response body with id as <id>
    Examples:
      | ProductTitle | id |
      | Shoes        | 21 |