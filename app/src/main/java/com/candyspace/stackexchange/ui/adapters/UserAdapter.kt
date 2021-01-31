package com.candyspace.stackexchange.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.extensions.listen
import com.candyspace.stackexchange.interfaces.UserClickListener
import com.candyspace.stackexchange.models.User
import kotlinx.android.synthetic.main.user_list_item.view.*

/**
 * Created by Prakash Nandi on 31/01/21.
 */
class UserAdapter(
    private val users: List<User>,
    private val userClickListener: UserClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    /**
     * custom view holder for user
     */
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reputationTv = view.reputation!!
        val usernameTv = view.username!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false) as View

        return UserViewHolder(view).listen { pos, _ -> // click listener for row
            userClickListener.onUserClick(users[pos], pos)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.reputationTv.text = user.reputation.toString()
        holder.usernameTv.text = user.display_name
    }
}

