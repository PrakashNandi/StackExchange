package com.candyspace.stackexchange.ui.fragments

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.ui.MainActivity
import com.candyspace.stackexchange.ui.adapters.UserAdapter
import com.candyspace.stackexchange.ui.utils.ViewActionUtils.Companion.waitFor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Prakash Nandi on 20/02/21.
 */
@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {

    @get:Rule
    var activityActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    private val userListToolbarTitle = "Stack Exchange"
    private val detailFragmentTitle = "User"

    @Before
    fun init() {
        activityActivityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun validateSearchHintText_recycler_view_user_details() {
        onView(withId(R.id.searchUserEt)).check(matches(withHint("Search Username")))
        onView(isRoot()).perform(waitFor(200))

        onView(withId(R.id.usersRecyclerView)).check(matches(isDisplayed()))

        if (getRecyclerViewCount() > 0) {
            onView(withId(R.id.usersRecyclerView)).perform(actionOnItemAtPosition<UserAdapter.UserViewHolder>(2, click()))

            // Validate user details data
            onView(withId(R.id.toolbarTitle)).check(matches(withText(detailFragmentTitle)))

            onView(withId(R.id.userImage)).check(matches(isDisplayed()))
            onView(withId(R.id.usernameTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.usernameText)).check(matches(isDisplayed()))
            onView(withId(R.id.reputationTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.reputationText)).check(matches(isDisplayed()))
            onView(withId(R.id.badgesTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.badgesText)).check(matches(isDisplayed()))
            onView(withId(R.id.locationTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.locationText)).check(matches(isDisplayed()))
            onView(withId(R.id.ageTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.ageText)).check(matches(isDisplayed()))
            onView(withId(R.id.creationDateTitle)).check(matches(isDisplayed()))
            onView(withId(R.id.creationDateText)).check(matches(isDisplayed()))
            onView(isRoot()).perform(waitFor(200))

            onView(isRoot()).perform(pressBack())
            onView(withId(R.id.toolbarTitle)).check(matches(withText(userListToolbarTitle)))
        }
    }

    private fun getRecyclerViewCount(): Int {
        val recyclerView =
            activityActivityTestRule.activity.findViewById(R.id.usersRecyclerView) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}