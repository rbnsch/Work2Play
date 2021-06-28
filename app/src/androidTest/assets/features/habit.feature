Feature: Habit(CRUD)
  This feature file is a CRUD and tests the creation and deletion of habits.

  Background:
    Given I am in Habits Tab

  Scenario Outline: Create Habit
    When I click on "+" button
    And AddHabit Screen is shown
    And I set name for Habit <habit>
    And I set amount of Habit <countCompl>
    And I set coins as reward for one part <singleCoins>
    And I set coins as reward for completely finished habit <rewardCoins>
    And I click "SAVE" button
    Then new habit is saved
    And I am moved back to habit tab
    And new habit is shown
    Examples:
      | habit | countCompl  | singleCoins | rewardCoins |
      | Jogging | 4         | 10          | 15          |
      | vacum   | 5         | 15          | 10          |
      | cooking not ordering takeaway | 10 | 25 | 50 |

  Scenario Outline: Delete Habit
    And at least one habit is already created
    When I hold click on habit <habit>
    And I click "DELETE"
    Then reward is deleted
    Examples:
      | habit |
      | jogging |
      | vacum |
      | cooking |
