package com.candyspace.stackexchange.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.candyspace.stackexchange.api.repositories.UsersRepository
import com.candyspace.stackexchange.models.BadgeCounts
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.models.UsersResponse
import com.candyspace.stackexchange.utils.TestCoroutineRule
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.concurrent.Callable


/**
 * Created by Prakash Nandi on 17/02/21.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<Response<UsersResponse>>

    @Mock
    private lateinit var usersViewModel: UsersViewModel

    @Mock
    private lateinit var repository: UsersRepository


    private var userList: MutableList<User> = mutableListOf()

    @Before
    fun setUp() {
        prepareUserList()
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val response = Response.success(UsersResponse(userList))
            doReturn(Observable.just(response))
                .`when`(repository)
                .getUsers()

            usersViewModel = UsersViewModel(repository)
            usersViewModel.getUsers().observeForever(apiUsersObserver)

            verify(repository).getUsers()
            verify(apiUsersObserver).onChanged(response)
            usersViewModel.getUsers().removeObserver(apiUsersObserver)
        }
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