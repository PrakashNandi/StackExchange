package com.candyspace.stackexchange.interfaces

/**
 * Created by Prakash Nandi on 30/01/21.
 */
interface ToolbarSetter {

    /**
     * To set title for the screen
     */
    fun setToolbarTitle(titleText: String)

    /**
     * To show or hide back button.
     */
    fun showBackButton(show: Boolean)
}