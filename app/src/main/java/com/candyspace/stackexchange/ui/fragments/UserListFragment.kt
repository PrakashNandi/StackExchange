package com.candyspace.stackexchange.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.candyspace.stackexchange.R

/**
 * Created by Prakash Nandi on 30/01/21.
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserListFragment : BaseFragment() {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_user_list
    }

    override fun initWidgets(fragmentView: View) {
        fragmentView.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_UserListFragment_to_UserDetailFragment)
        }
    }
}