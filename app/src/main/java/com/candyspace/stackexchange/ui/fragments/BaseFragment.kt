package com.candyspace.stackexchange.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.candyspace.stackexchange.interfaces.ToolbarSetter

/**
 * Created by Prakash Nandi on 30/01/21.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets(view)
    }

    /**
     * to set text for toolbar
     */
    protected fun setToolbarTitle(title: String = "") {
        val toolBarSetter = activity as ToolbarSetter
        toolBarSetter.setToolbarTitle(title)
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun initWidgets(fragmentView: View)
}