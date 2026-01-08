Feature: User Registration Functionality with DataProviders

  @dataproviders
  Scenario Outline: Register new users using DataProviders
    Given User is on the registration page for multiple registrations
    When User enters registration details from row "<rowIndex>"
    Then Registration should be successful

    Examples: 
      | rowIndex |
      |        1 |
      |        2 |
