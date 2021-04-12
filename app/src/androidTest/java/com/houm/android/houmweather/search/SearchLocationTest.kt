package com.houm.android.houmweather.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.houm.android.houmweather.R
import com.houm.android.houmweather.feature.dashboard.DashboardActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchLocationTest {


    @get:Rule
    var activityRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun test_IsDashboardActivityLaunched() {
        onView(withId(R.id.latlng_textview)).check(matches(isDisplayed()));
    }

}