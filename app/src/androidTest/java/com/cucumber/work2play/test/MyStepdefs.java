package com.cucumber.work2play.test;


import android.app.Activity;
import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.EditText;

import android.app.Activity;
import android.content.Intent;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;
import com.example.work2play.MainActivity;
import com.example.work2play.R;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.*;

import com.example.work2play.MainActivity;



import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.internal.matchers.TypeSafeMatcher;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.Assert.assertNotNull;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


public class MyStepdefs{

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    int i = 0;

    private final ViewInteraction mFab = onView(withId(R.id.fab));

    private Activity activity;

    @Before("@reward-feature")
    public void setup() {
        mainActivityActivityTestRule.launchActivity(new Intent());
        activity = mainActivityActivityTestRule.getActivity();

    }

    @Before("@buy-rewards-feature")
    public void setupBuyRewards() {
        mainActivityActivityTestRule.launchActivity(new Intent());
        activity = mainActivityActivityTestRule.getActivity();

    }

    @After("@reward-feature")
    public void tearDown() {
        mainActivityActivityTestRule.finishActivity();
    }

    @After("@buy-rewards-feature")
    public void tearDownRewards() {
        mainActivityActivityTestRule.finishActivity();
    }

    @Given("^I am in the Rewards Tab")
    public void iAmInTheRewardsTab() {
        assertNotNull(activity);
        onView(withText("Rewards")).perform(click());
    }

    @When("^I click the add button$")
    public void click_add() {
        onView(withId(R.id.fab)).perform(click());
        // mFab.perform(click());
    }


    @And("^I enter a Reward Name$")
    public void iEnterARewardName() {
        i++;
        
    }

    @And("^I set the wanted coins$")
    public void iSetTheWantedCoins() {
        i++;
    }

    @Then("^I get added this reward$")
    public void iGetAddedThisReward() {
        i++;
    }
}
