package com.candyspace.stackexchange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.candyspace.stackexchange.api.repositories.UsersRepository
import java.lang.reflect.Constructor

/**
 * Created by Prakash Nandi on 17/02/21.
 */
class UsersViewModelFactory(private val usersRepository: UsersRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor: Constructor<T> = modelClass.getDeclaredConstructor(UsersRepository::class.java)
            return constructor.newInstance(usersRepository)
        } catch (e: Exception) {
            throw e
        }
    }
}