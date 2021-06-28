Feature: Finish Task
  This feature file tests the completion of tasks and whether it is deleted after completion.

  Background:
    Given I am in the Tasks Tab
    When I hold click on a Task

  @finish-task-feature
  Scenario: Finish unique Task
    And Task is not repeatable
    And I click on the Finish Task Button
    Then Coins are added
    And I go back to the Task Tab
    And Task is removed

  @finish-task-feature
  Scenario: Finish repeating Task
    And Task is repeatable
    And I click on the Finish Task Button
    Then Coins are added
    And I go back to the Task Tab