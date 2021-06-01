Feature: Finish Task

  Background:
    Given I am in the Tasks Tab
    When I hold click on a Task

  @finish-rewards-feature
  Scenario: Finish unique Task
    And Task is not repeatable
    And I click on the Finish Task Button
    Then Coins are added
    And I go back to the Task Tab
    And Task is removed

  @finish-rewards-feature
  Scenario: Finish repeating Task
    And Task is repeatable
    And I click on the Finish Task Button
    Then Coins are added
    And I go back to the Task Tab

