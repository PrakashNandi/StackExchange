package com.candyspace.stackexchange.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.candyspace.stackexchange.api.UsersApiService
import com.candyspace.stackexchange.api.repositories.UsersRepository
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
import org.mockito.Mockito.*
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

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val usersResponse = UsersResponse(emptyList())
            doReturn(Observable.just(Response.success(usersResponse)))
                .`when`(repository)
                .getUsers()

            usersViewModel = UsersViewModel(repository)
            usersViewModel.getUsers().observeForever(apiUsersObserver)

            verify(repository).getUsers()
            verify(apiUsersObserver).onChanged(Response.success(usersResponse))
            usersViewModel.getUsers().removeObserver(apiUsersObserver)
        }
    }
}