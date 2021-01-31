package com.candyspace.stackexchange.api.repositories

import com.candyspace.stackexchange.models.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import retrofit2.Response

/**
 * Created by Prakash Nandi on 31/01/21.
 */
object RxUsers {
    private val publisher = PublishSubject.create<Map<String, Response<User>>>()

    /**
     * publish users responses
     */
    fun publish(event: Map<String, Response<User>>) {
        publisher.onNext(event)
    }

    /**
     * This needs to be used to subscribe for the observable
     */
    fun listen() : Observable<Map<String, Response<User>>> {
        return publisher
    }
}