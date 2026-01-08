@userProfile @carRating
Feature: User Profile, and Car Rating Functionality

  Background: 
    Given User is logged with "Ryker" and  "Pass@1234" into the application


  Scenario: Update user profile details
    When User clicks on Profile link
    And User enters the following updated details:
      | field   | value          |
      | Gender  | Male           |
      | Age     |             37 |
      | Address | Delhi          |
      | Phone   |     9876543210 |
      | Hobby   | Reading Comics |
    And User clicks on the Save button
    Then the profile should be updated successfully

  Scenario: Rate a popular car model
    When User clicks in Popular Make section
    And User clicks on "Reventón" car
    And User enters a comment "Amazing car, loved the performance!"
    And User clicks on the Vote button
    Then vote should be submitted successfully
