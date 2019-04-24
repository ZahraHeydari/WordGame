package com.android.wordgame.ui.intro

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.android.wordgame.R
import com.android.wordgame.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class IntroFragmentTest{


    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        activityRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun startGameButton_IsDisplayed(){
        onView(withId(R.id.intro_start_game_button)).check(matches(isDisplayed()))
    }

    @Test
    fun startGameButton_OnClick() {
        onView(withId(R.id.intro_start_game_button)).perform(click())
    }
}