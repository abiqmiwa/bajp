package com.dicoding.picodiploma.bajp2.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.dicoding.picodiploma.bajp2.R
import com.dicoding.picodiploma.bajp2.etc.EspressoIdlingResource
import org.junit.After
import org.junit.Test

import org.junit.Before

class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rated))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_duration))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rated))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavoriteMovie() {
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        Espresso.onView(withText("Favorite")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShow() {
        delayTwoSecond()
        Espresso.onView(withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        Espresso.onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        Espresso.onView(withText("Favorite")).perform(ViewActions.click())
        Espresso.onView(withText(R.string.tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_fav_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun delayTwoSecond() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}