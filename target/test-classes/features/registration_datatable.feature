Feature: User Registration Functionality

  @datatable
  Scenario: Register a new user
    Given User is on the registration page
    When User enters the following details:
      | field            | value     |
      | Login            | Mercerr   |
      | First Name       | demo      |
      | Last Name        | demo      |
      | Password         | Pass@1234 |
      | Confirm Password | Pass@1234 |
    And User clicks on the Register button
    Then The registration should be successful
