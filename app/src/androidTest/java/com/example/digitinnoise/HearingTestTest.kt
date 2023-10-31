package com.example.digitinnoise

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.digitinnoise.hearingTest.presentation.fragments.HomeScreen
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HearingTestTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun starting_the_test_navigates_to_the_test_screen() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val homeScreenScenario = launchFragmentInContainer<HomeScreen>()

        homeScreenScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.start_test_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.testScreen)
    }
}