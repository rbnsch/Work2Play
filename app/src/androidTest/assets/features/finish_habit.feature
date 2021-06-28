Feature: Finish Habit

  Background:
    Given I am in Habits Tab

Scenario Outline: Finish Habit completely
And at least one habit is already created
When I hold click on habit <habit>
And I click on "FINISH"
And habit has exactly one point left to completely finish <finishedcount> <countCompl>
Then reward for one part and for completely finishing are added to balance <singelCoin> <rewardCoins> <balance>
And counter is raised by 1 <finishedcount>
And I go back to Habits Tab
Examples:
| habit | finishedcount | countCompl | singelCoin | rewardCoins | balance |
| Jogging | 9           | 10         | 15         | 25          | 5       |
| vacum   | 5           | 6          | 15         | 10          | 0       |
| cooking not ordering takeaway | 9 | 10 | 25     | 50          | 75      |

Scenario Outline: Finish Habit
And at least one habit is already created
When I hold click on habit <habit>
And I click on "FINISH"
And habit has more than one point left to completely finish <finishedcount> <countCompl>
Then reward for one part is added to balance <singelCoin> <balance>
And counter is raised by 1 <finishedcount>
And I go back to Habits Tab
Examples:
| habit | finishedcount | countCompl | singelCoin | balance |
| Jogging | 9           | 10         | 15         | 5       |
| vacum   | 2           | 6          | 15         | 0       |
| cooking not ordering takeaway | 6 | 10 | 25     | 75      |

