package com.android.wordgame.ui.game

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
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
class GameFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun correctImage_isEnabled() {
        onView(withId(R.id.intro_start_game_button)).perform(click())
        onView(withId(R.id.game_correct_image_view)).check(matches(isEnabled()))
    }

    @Test
    fun correctImage_OnClick() {
        onView(withId(R.id.intro_start_game_button)).perform(click())
        onView(withId(R.id.game_point_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.game_timer_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.game_question_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.game_answer_text_view)).check(matches(isDisplayed()))
        Thread.sleep(100)
        onView(ViewMatchers.withId(R.id.game_correct_image_view)).perform(click())
    }

    @Test
    fun inCorrectImage_isEnabled() {
        onView(withId(R.id.intro_start_game_button)).perform(click())
        onView(withId(R.id.game_incorrect_image_view)).check(matches(isEnabled()))
    }

    @Test
    fun inCorrectImage_OnClick() {
        onView(withId(R.id.intro_start_game_button)).perform(click())
        onView(withId(R.id.game_point_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.game_timer_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.game_question_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.game_answer_text_view)).check(matches(isDisplayed()))
        Thread.sleep(100)
        onView(ViewMatchers.withId(R.id.game_incorrect_image_view)).perform(click())
    }
}