package com.candyspace.stackexchange.ui.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.api.UsersApiService
import com.candyspace.stackexchange.api.repositories.UsersRepository
import com.candyspace.stackexchange.mockdata.UsersData
import com.candyspace.stackexchange.models.BadgeCounts
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.models.UsersResponse
import com.candyspace.stackexchange.ui.MainActivity
import com.candyspace.stackexchange.ui.adapters.UserAdapter
import com.candyspace.stackexchange.ui.utils.ViewActionUtils
import com.candyspace.stackexchange.viewmodel.UsersViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkClass
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Created by Prakash Nandi on 20/02/21.
 */
@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {

    @get:Rule
    var activityActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @MockK
    private lateinit var usersApiService: UsersApiService

    var userList: MutableList<User> = mutableListOf()

    val detailFragmentTitle = "User"

    @Before
    fun init() {
        prepareUserList()
        MockKAnnotations.init(this, relaxUnitFun = true)
        val usersResponse = UsersResponse(UsersData.userList)
        every {
            usersApiService.getUsers("desc", "reputation", "stackoverflow")
        } returns Observable.just(Response.success(usersResponse))
        activityActivityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun validateSearchHintText() {
        onView(withId(R.id.searchUserEt)).check(matches(withHint("Search Username")))
    }

    @Test
    fun test_isRecyclerviewVisible() {
        onView(withId(R.id.usersRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectItem() {
        onView(withId(R.id.usersRecyclerView)).perform(actionOnItemAtPosition<UserAdapter.UserViewHolder>(2, click()))
        onView(withId(R.id.toolbarTitle)).check(matches(withText(detailFragmentTitle)))
    }

    private fun prepareUserList() {
        val badgeCount = BadgeCounts()
        badgeCount.bronze = 8887
        badgeCount.silver = 8656
        badgeCount.gold = 785

        val user = User()
        user.badge_counts = badgeCount
        user.account_id = 11683
        user.is_employee = false
        user.last_modified_date = 1613736300
        user.last_access_date = 1613736300
        user.reputation_change_year = 10285
        user.reputation_change_quarter = 10285
        user.reputation_change_month = 4040
        user.reputation_change_week = 1380
        user.reputation_change_day = 200
        user.reputation = 1240975
        user.creation_date = 1222430705
        user.user_type = "registered"
        user.user_id = 22656
        user.accept_rate = 86
        user.location = "Woking, UK"
        user.website_url = "http://csharpindepth.com"
        user.link = "https://stackoverflow.com/users/22656/jon-skeet"
        user.profile_image = "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG"
        user.display_name = "Jon Skeet"

        userList.add(user)

        val badgeCount1 = BadgeCounts()
        badgeCount.bronze = 8887
        badgeCount.silver = 8656
        badgeCount.gold = 785

        val user1 = User()
        user.badge_counts = badgeCount1
        user.account_id = 11700
        user.is_employee = false
        user.last_modified_date = 1613736300
        user.last_access_date = 1613736300
        user.reputation_change_year = 10285
        user.reputation_change_quarter = 10285
        user.reputation_change_month = 4040
        user.reputation_change_week = 1380
        user.reputation_change_day = 200
        user.reputation = 1240975
        user.creation_date = 1222430705
        user.user_type = "registered"
        user.user_id = 22659
        user.accept_rate = 86
        user.location = "Woking, UK"
        user.website_url = "http://csharpindepth.com"
        user.link = "https://stackoverflow.com/users/22656/jon-skeet"
        user.profile_image = "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG"
        user.display_name = "Robert Parker"

        userList.add(user1)
    }
}