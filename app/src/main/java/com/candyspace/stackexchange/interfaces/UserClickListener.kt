package com.candyspace.stackexchange.interfaces

import com.candyspace.stackexchange.models.User

/**
 * Created by Prakash Nandi on 31/01/21.
 */
interface UserClickListener {
    /**
     * User list screen's item click listener
     */
    fun onUserClick(journey: User, position: Int)
}