Feature: Add Reward

    @reward-feature
    Scenario: Successfully Add Reward

    Given I am in the Rewards Tab
    When I click the add button
    And I enter a Reward Name
    And I set the wanted coins
    Then I get added this reward

