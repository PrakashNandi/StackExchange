package com.candyspace.stackexchange.api

import com.candyspace.stackexchange.BuildConfig.BASE_URL
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.models.UsersResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Prakash Nandi on 31/01/21.
 */
interface UsersApiService {

    /**
     * API service to get list of delayed journeys
     */
    @GET("/users")
    fun getUsers(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String
    ): Observable<Response<UsersResponse>>

    companion object Factory {
        fun create(): UsersApiService {
            val client = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .cache(null)
                .addInterceptor { chain: Interceptor.Chain ->
                    val request =
                        chain.request().newBuilder()
                            .addHeader("key", "Kh4Ib4UQSQO8YMsUfkLzrQ((").build()
                    chain.proceed(request)
                }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(UsersApiService::class.java)
        }
    }
}