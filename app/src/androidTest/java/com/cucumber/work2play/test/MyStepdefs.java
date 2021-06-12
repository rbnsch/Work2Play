package com.cucumber.work2play.test;


import android.app.Activity;
import android.content.Intent;
import androidx.test.espresso.Root;
import androidx.test.rule.ActivityTestRule;



import androidx.test.espresso.ViewInteraction;

import com.example.work2play.MainActivity;
import com.example.work2play.R;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.hamcrest.Matcher;
import org.junit.Rule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static com.example.work2play.MainActivity.setCoins;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;



public class MyStepdefs{

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    private final ViewInteraction mFab = onView(withId(R.id.fab));

    private Activity activity;

    @Before("@buy-rewards-feature")
    public void setupBuyRewards() {

    }

    @After("@buy-rewards-feature")
    public void tearDownRewards() {
        mainActivityActivityTestRule.finishActivity();
    }

    @Given("^I am in the Rewards Tab")
    public void iAmInTheRewardsTab() {
        mainActivityActivityTestRule.launchActivity(new Intent());
        activity = mainActivityActivityTestRule.getActivity();
        assertNotNull(activity);
        onView(withText("Rewards")).perform(click());
    }

    @When("^I hold click on a Reward$")
    public void iHoldClickOnAReward() {
        onData(hasToString(startsWith("")))
                .inAdapterView(withId(R.id.listRewards)).atPosition(0)
                .perform(longClick());
    }

    @And("^I click on the Buy Reward button$")
    public void iClickOnTheBuyRewardButton() {
        onView(withText("BUY")).inRoot(isPopupWindow()).perform(click());
    }

    private Matcher<Root> isPopupWindow() {
        return isPlatformPopup();
    }





/*
    @When("^I click the add button$")
    public void click_add() {
        onView(withId(R.id.fab)).perform(click());
        // mFab.perform(click());
    }
*/



    @And("^I have enough coins$")
    public void iHaveEnoughCoins() {
        setCoins(200);
    }

    @And("^the reward is set to unique$")
    public void theRewardIsSetToUnique() {

    }

    @Then("^the required coins are subtracted$")
    public void theRequiredCoinsAreSubtracted() {

    }

    @And("^I go back to the Rewards Tab$")
    public void iGoBackToTheRewardsTab() {

    }

    @And("^the Reward is removed$")
    public void theRewardIsRemoved() {

    }

    @And("^the reward is set to non unique$")
    public void theRewardIsSetToNonUnique() {

    }

    @And("^I don't have enough coins$")
    public void iDonTHaveEnoughCoins() {
        setCoins(1);
    }

    @And("^toast: \"([^\"]*)\" is shown$")
    public void toastIsShown(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }








    @Before("@finish-task-feature")
    public void setupFinishTasks() {

    }

    @After("@finish-task-feature")
    public void tearDownTasks() {
        mainActivityActivityTestRule.finishActivity();
    }



    @Given("^I am in the Tasks Tab$")
    public void iAmInTheTasksTab() {
        mainActivityActivityTestRule.launchActivity(new Intent());
        activity = mainActivityActivityTestRule.getActivity();
        assertNotNull(activity);
        onView(withText("Tasks")).perform(click());
    }

    @When("^I hold click on a Task$")
    public void iHoldClickOnATask() {
        onData(hasToString(startsWith("")))
                .inAdapterView(withId(R.id.listTasks)).atPosition(0)
                .perform(longClick());
    }

    @And("^I click on the Finish Task Button$")
    public void iClickOnTheFinishTaskButton() {
        onView(withText("FINISH")).inRoot(isPopupWindow()).perform(click());
    }

    @And("^Task is not repeatable$")
    public void taskIsNotRepeatable() {

    }

    @Then("^Coins are added$")
    public void coinsAreAdded() {

    }

    @And("^I go back to the Task Tab$")
    public void iGoBackToTheTaskTab() {

    }

    @And("^Task is repeatable$")
    public void taskIsRepeatable() {

    }

    @And("^Task is removed$")
    public void taskIsRemoved() {
    }
}