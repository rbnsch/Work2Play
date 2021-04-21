Feature: Finish Task

  Background:
    Given I am in the Tasks Tab
    When I hold click on a Task
    And I click on the Finish Task button

  Scenario: Finish unique Task
    And Task is not set to "Repeating Task"
    Then
