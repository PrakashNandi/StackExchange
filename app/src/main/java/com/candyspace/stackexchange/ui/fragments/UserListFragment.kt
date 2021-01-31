package com.candyspace.stackexchange.ui.fragments

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.interfaces.UserClickListener
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.ui.adapters.UserAdapter
import com.candyspace.stackexchange.utils.NetworkUtils.Companion.isInternetAvailable
import com.candyspace.stackexchange.viewmodel.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_user_list.*


/**
 * Created by Prakash Nandi on 30/01/21.
 * Fragment for displaying users list.
 */
class UserListFragment : BaseFragment(), UserClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var userAdapter: UserAdapter
    private var userList: MutableList<User> = mutableListOf()
    private lateinit var usersViewModel: UsersViewModel

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_user_list
    }

    override fun initWidgets(fragmentView: View) {
        setToolbarTitle(getString(R.string.app_name))
        showBackButton(false)
        val layoutManager = LinearLayoutManager(context)
        usersRecyclerView.layoutManager = layoutManager
        userAdapter = UserAdapter(userList, this)
        usersRecyclerView.adapter = userAdapter

        usersSwipeRefreshLayout.setOnRefreshListener(this)

        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        getAllUsers()
    }

    /**
     * User list screen's item click listener
     */
    override fun onUserClick(user: User, position: Int) {
        val bundle = bundleOf("USER" to user)
        findNavController().navigate(R.id.action_UserListFragment_to_UserDetailFragment, bundle)
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        getAllUsers()
    }

    private fun getAllUsers() {
        if (isInternetAvailable(requireActivity())) {
            usersViewModel.getUsers().observe(this, Observer { usersResponse ->
                if (usersResponse.isSuccessful) {
                    userList.clear()
                    userList.addAll(usersResponse.body()?.items as MutableList<User>)
                    userAdapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(userListConstraintLayout, usersResponse.errorBody().toString(), Snackbar.LENGTH_LONG).show()
                }
            })
        } else {
            Snackbar.make(userListConstraintLayout, getString(R.string.no_internet_error), Snackbar.LENGTH_LONG).show()
        }
    }
}