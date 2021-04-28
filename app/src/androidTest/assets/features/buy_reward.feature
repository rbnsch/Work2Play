Feature: Buy Reward


  Background:
    Given I am in the Rewards Tab
    When I hold click on a Reward
    And I click on the Buy Reward button

  @buy-rewards-feature
  Scenario: Successfully buy unique Reward
    And I have enough coins
    And the reward is set to unique
    Then the required coins are subtracted
    And I go back to the Rewards Tab
    And the Reward is removed


  Scenario: Successfully buy non unique Reward
    And I have enough coins
    And the reward is set to non unique
    Then the required coins are subtracted
    And I go back to the Rewards Tab


  Scenario: Unsuccessfully buy Reward
    And I don't have enough coins
    Then I go back to the Rewards Tab
    And toast: "not enough coins" is shown
