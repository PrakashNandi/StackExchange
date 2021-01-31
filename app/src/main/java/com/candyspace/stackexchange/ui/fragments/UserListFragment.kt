package com.candyspace.stackexchange.ui.fragments

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.interfaces.UserClickListener
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.ui.adapters.UserAdapter
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * Created by Prakash Nandi on 30/01/21.
 * Fragment for displaying users list.
 */
class UserListFragment : BaseFragment(), UserClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var userAdapter: UserAdapter
    private var userList: MutableList<User> = mutableListOf()

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_user_list
    }

    override fun initWidgets(fragmentView: View) {
        val layoutManager = LinearLayoutManager(context)
        usersRecyclerView.layoutManager = layoutManager
        userAdapter = UserAdapter(userList, this)
        usersRecyclerView.adapter = userAdapter

        usersSwipeRefreshLayout.setOnRefreshListener(this)
    }

    /**
     * User list screen's item click listener
     */
    override fun onUserClick(journey: User, position: Int) {
        findNavController().navigate(R.id.action_UserListFragment_to_UserDetailFragment)
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        getAllUsers()
    }

    private fun getAllUsers() {
        TODO("Not yet implemented")
    }
}