package com.carlosjr.eventsapp.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.carlosjr.eventsapp.R
import org.junit.Rule
import org.junit.Test

class InitialActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(InitialActivity::class.java)

    @Test
    fun verifyImageViewEventsLogo_isDisplayed_openInitialScreen() {
        Espresso.onView(withId(R.id.image_view_events_logo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun verifyImageViewEventsLogo_contentDescription_openInitialScreen() {
        Espresso.onView(withId(R.id.image_view_events_logo))
            .check(ViewAssertions.matches(withContentDescription(R.string.initial_logo_accessibility)))
    }

}