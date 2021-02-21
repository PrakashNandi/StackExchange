package com.candyspace.stackexchange.ui.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher


/**
 * Created by Prakash Nandi on 21/02/21.
 */
class ViewActionUtils {

    companion object {
        fun waitFor(millis: Long): ViewAction? {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return isRoot()
                }

                override fun getDescription(): String {
                    return "Wait for $millis milliseconds."
                }

                override fun perform(uiController: UiController, view: View?) {
                    uiController.loopMainThreadForAtLeast(millis)
                }
            }
        }

        fun clickChildViewWithId(id: Int): ViewAction? {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController?, view: View) {
                    val v: View = view.findViewById(id)
                    v.performClick()
                }
            }
        }
    }
}