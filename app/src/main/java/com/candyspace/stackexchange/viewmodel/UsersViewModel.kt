package com.candyspace.stackexchange.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candyspace.stackexchange.api.providers.UsersRepositoryProvider
import com.candyspace.stackexchange.api.repositories.UsersRepository
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.models.UsersResponse
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class UsersViewModel(private val usersRepository: UsersRepository) : ViewModel() {

        private val mtlUsers: MutableLiveData<Response<UsersResponse>> by lazy {
            MutableLiveData<Response<UsersResponse>>().also {
            }
        }

        init {
            processUsersRequest()
        }

        /**
         * Request repository to get the users list.
         */
        private fun processUsersRequest() {

            viewModelScope.launch {
                usersRepository.getUsers().observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ usersResponse ->
                        mtlUsers!!.value = usersResponse
                    }, { error ->
                        Log.d("TAG", "error : " + error.message + " " + error.cause.toString())
                    })
            }
        }

        /**
         * This API should be called from view to get delayed journey data
         */
        fun getUsers(): LiveData<Response<UsersResponse>> {
            return mtlUsers!!
        }
}