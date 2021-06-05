Feature:Task(CRUD)
  Background:
    Given User is in the Tasks tab

  Scenario: Create Task
    When User clicks on the "+" button
    And AddTask screen is shown
    And user sets name
    And user sets rewards
    And user sets date
    And user selects repeatable or not
    And user selects project
    And user clicks the  "SAVE" button
    Then new task is sored
    And user is moved back to Tasks tab
    And new Task is shown

  Scenario: Delete Task
    Given at least one Task is already created
    When user hold click on a task
    And user presses the "DELETE" button
    Then Task is deleted
    And menu disappears

  Scenario: Finishing non repeatable Task
    Given at least one Task is already created
    And Task is non repeatable
    When user hold clicks on a task
    And user presses the "FINISH" button
    Then the earned coins are added
    And the task is deleted
    And menu disappears

  Scenario: Finishing repeatable Task
    Given at least one Task is already created
    And Task is repeatable
    When user hold clicks on a task
    And user presses the "FINISH" button
    Then the earned coins are added
    And menu disappears