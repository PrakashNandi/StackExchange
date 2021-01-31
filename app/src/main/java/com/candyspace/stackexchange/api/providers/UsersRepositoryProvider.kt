package com.candyspace.stackexchange.api.providers

import com.candyspace.stackexchange.api.UsersApiService
import com.candyspace.stackexchange.api.repositories.UsersRepository

/**
 * Created by Prakash Nandi on 31/01/21.
 */
object UsersRepositoryProvider {

    /**
     * This needs to be used to create an instance of UsersRepository class
     */
    fun usersRepositoryProvider(): UsersRepository {
        val apiService = UsersApiService.create()
        return UsersRepository(apiService)
    }
}