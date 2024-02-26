package com.carlosjr.eventsapp.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.carlosjr.eventsapp.R
import org.junit.Rule
import org.junit.Test

class DetailsActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(DetailsActivity::class.java)

    @Test
    fun verifyIncludeActionBar_isDisplayed_openDetailsScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.include_action_bar))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun verifyIncludeEventDetail_isDisplayed_openHomeScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.include_event_detail))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun verifyFabActionButton_isDisplayed_openDetailsScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.floatingActionButton))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun verifyCustomDialog_isDisplayed_after_click_fabActionButton_openDetailsScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.custom_dialog))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun verifyFabActionButton_contentDescription_openInitialScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.floatingActionButton))
            .check(ViewAssertions.matches(
                ViewMatchers.withContentDescription(R.string.details_check_in_accessibility)))
    }

}