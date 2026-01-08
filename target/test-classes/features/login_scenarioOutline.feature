Feature: View Login name for logged-in user

  @outline
  Scenario Outline: View logged-in username on profile page
    Given User is on the login page
    When User logs in with the "<Login>" and "<Password>"
    And User clicks on the Profile link
    Then User should see the "<Login>" name displayed

    Examples: 
      | Login     | Password  |
      | testdesk  | Pass@1234 |
      | Peterry   | Pass@1234 |
      | Zoey      | Pass@1234 |
      | testdeskk | Pass@1234 |
