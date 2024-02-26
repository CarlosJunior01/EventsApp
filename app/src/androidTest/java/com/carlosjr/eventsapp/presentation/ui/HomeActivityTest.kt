package com.carlosjr.eventsapp.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.carlosjr.eventsapp.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun verifyTextViewCategoryTitle_isDisplayed_openHomeScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.text_events_category))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun verifyRecyclerViewEvents_isDisplayed_openHomeScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_events))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRecyclerViewClickItem() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_events)).perform(
            actionOnItemAtPosition<EventsAdapter.EventViewHolder>(0, ViewActions.click())
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun verifyTextViewCategoryTitle_contentDescription_openInitialScreen() {
        Espresso.onView(ViewMatchers.withId(R.id.text_events_category))
            .check(ViewAssertions.matches(ViewMatchers.withContentDescription(R.string.home_events_category_accessibility)))
    }

}