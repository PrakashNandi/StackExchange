package com.candyspace.stackexchange.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.extensions.setDivider
import com.candyspace.stackexchange.interfaces.UserClickListener
import com.candyspace.stackexchange.models.User
import com.candyspace.stackexchange.ui.adapters.UserAdapter
import com.candyspace.stackexchange.utils.NetworkUtils.Companion.isInternetAvailable
import com.candyspace.stackexchange.viewmodel.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created by Prakash Nandi on 30/01/21.
 * Fragment for displaying users list.
 */
class UserListFragment : BaseFragment(), UserClickListener, TextWatcher {

    private lateinit var userAdapter: UserAdapter
    private var userList: MutableList<User> = mutableListOf()
    private var userListAll: MutableList<User> = mutableListOf()
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllUsers()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_user_list
    }

    override fun initWidgets(fragmentView: View) {
        setToolbarTitle(getString(R.string.app_name))
        showBackButton(false)
        val layoutManager = LinearLayoutManager(context)
        usersRecyclerView.layoutManager = layoutManager
        usersRecyclerView.setDivider(R.drawable.recycler_view_divider)
        userAdapter = UserAdapter(userList, this)
        usersRecyclerView.adapter = userAdapter

        searchUserEt.addTextChangedListener(this)
    }

    /**
     * User list screen's item click listener
     */
    override fun onUserClick(user: User, position: Int) {
        val bundle = bundleOf("USER" to user)
        findNavController().navigate(R.id.action_UserListFragment_to_UserDetailFragment, bundle)
    }

    private fun getAllUsers() {
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        if (isInternetAvailable(requireActivity())) {
            usersViewModel.getUsers().observe(this, Observer { usersResponse ->
                if (usersResponse.isSuccessful) {
                    userList.clear()
                    userListAll.clear()
                    userListAll = usersResponse.body()?.items as MutableList<User>
                    userList.addAll(userListAll)
                    userAdapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(userListConstraintLayout, usersResponse.errorBody().toString(), Snackbar.LENGTH_LONG).show()
                }
            })
        } else {
            Snackbar.make(userListConstraintLayout, getString(R.string.no_internet_error), Snackbar.LENGTH_LONG).show()
        }
    }

    override fun afterTextChanged(s: Editable?) {

        GlobalScope.launch {
            usersRecyclerView.stopScroll()
            userList.clear()
            if (s.toString().isNotEmpty()) {
                userList.addAll(userListAll.filter { user -> user.display_name?.toLowerCase()!!.contains(s.toString().toLowerCase()) })
            } else {
                userList.addAll(userListAll)
            }
        }

        activity?.runOnUiThread {
            userAdapter!!.notifyDataSetChanged()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}