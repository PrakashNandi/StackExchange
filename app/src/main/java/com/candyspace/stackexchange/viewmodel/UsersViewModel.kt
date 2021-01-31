package com.candyspace.stackexchange.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.candyspace.stackexchange.api.providers.UsersRepositoryProvider
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.models.UsersResponse
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_list.*
import retrofit2.Response

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class UsersViewModel : ViewModel() {

        private val mtlUsers: MutableLiveData<Response<UsersResponse>> by lazy {
            MutableLiveData<Response<UsersResponse>>().also {
            }
        }

        /**
         * Request repository to get the users list.
         */
        private fun processUsersRequest() {
            val repository = UsersRepositoryProvider
                .usersRepositoryProvider()

            repository.getUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ usersResponse ->
                    mtlUsers!!.value = usersResponse
                }, { error ->
                    Log.d("TAG", "error : " + error.message + " " + error.cause.toString())
                })
        }

        /**
         * This API should be called from view to get delayed journey data
         */
        fun getUsers(): LiveData<Response<UsersResponse>> {
            processUsersRequest()
            return mtlUsers!!
        }
}