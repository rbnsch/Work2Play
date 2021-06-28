Feature: Task(CRUD)

  Background:
    Given I am in the Tasks tab

  Scenario Outline: Create Task
    When I click on the "+" button
    And AddTask screen is shown
    And I set name for task <task>
    And I set amount of coins as rewards <coins>
    And I set date <date>
    And I select repeatable or not <repeatable>
    And I select project <project>
    And I click the  "SAVE" button
    Then new task is saved
    And I am moved back to Tasks tab
    And new Task is shown
    Examples:
      | task                | coins | date      | repeatable | project |
      | finish CV           | 25    | 20.06.21  | no         | job     |
      | clean room          | 10    | 04.09.22  | yes        | home    |
      | bring car to garage | 15    | 05.08.21  | no         | car     |

  Scenario Outline: Delete Task
    And at least one Task is already created
    When I hold click on a task <task>
    And I presses the "DELETE" button
    Then Task is deleted
    And menu disappears
    Examples:
      | task |
      | finish CV           |
      | clean room          |
      | bring car to garage |

