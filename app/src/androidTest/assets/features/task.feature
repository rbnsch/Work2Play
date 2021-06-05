Feature:Task(CRUD)
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
    Then new task is sored
    And I am moved back to Tasks tab
    And new Task is shown
    Examples:
      | task                | coins | date      | repeatable | project |
      | finish CV           | 25    | 20.06.21  | no         | job     |
      | clean room          | 10    | 04.09.22  | yes        | home    |
      | bring car to garage | 15    | 05.08.21  | no         | car     |

  Scenario Outline: Delete Task
    Given at least one Task is already created
    When I hold click on a task <task>
    And I presses the "DELETE" button
    Then Task is deleted
    And menu disappears
    Examples:
      | task |
      | finish CV           |
      | clean room          |
      | bring car to garage |

  Scenario Outline: Finishing non repeatable Task
    Given at least one Task is already created
    And Task is non repeatable <repeatable>
    When I hold click on a task <task>
    And I press the "FINISH" button
    Then the earned coins are added <coins>
    And the task is deleted
    And menu disappears
    Examples:
      | task                | repeatable   | coins |
      | finish CV           | no           | 25    |
      | clean room          | yes          | 10    |
      | bring car to garage | no           | 15    |


  Scenario Outline: Finishing repeatable Task
    Given at least one Task is already created
    And Task is repeatable <repeatable>
    When I hold click on a task <task>
    And I press the "FINISH" button
    Then the earned coins are added <coins>
    And menu disappears
    Examples:
      | task                | repeatable   | coins |
      | finish CV           | no           | 25    |
      | clean room          | yes          | 10    |
      | bring car to garage | no           | 15    |