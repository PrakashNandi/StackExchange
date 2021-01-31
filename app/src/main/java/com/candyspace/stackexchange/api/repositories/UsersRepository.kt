package com.candyspace.stackexchange.api.repositories

import com.candyspace.stackexchange.api.UsersApiService
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.models.UsersResponse
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class UsersRepository(private val usersApiService: UsersApiService) {

    /**
     * repository API to get list of users
     */
    fun getUsers(
        order: String = "desc",
        sort: String = "reputation",
        site: String = "stackoverflow"
    ): Observable<Response<UsersResponse>> {
        return usersApiService.getUsers(order, sort, site)
    }
}