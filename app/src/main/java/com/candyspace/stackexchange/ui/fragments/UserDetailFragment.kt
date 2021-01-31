package com.candyspace.stackexchange.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.models.User
import kotlinx.android.synthetic.main.fragment_user_detail.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Prakash Nandi on 30/01/21.
 * Fragment to display user details.
 */
class UserDetailFragment : BaseFragment() {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_user_detail
    }

    override fun initWidgets(fragmentView: View) {
        setToolbarTitle(getString(R.string.user))
        showBackButton(true)
        var user = arguments?.getParcelable<User>("USER")
        Glide.with(this)
            .load(user?.profile_image)
            .dontAnimate()
            .into(userImage);
        usernameText.text = user?.display_name
        reputationText.text = user?.reputation.toString()
        badgesText.text = "${ user?.badge_counts?.bronze.toString() } (Bronze), ${ user?.badge_counts?.silver.toString() } (Silver), ${ user?.badge_counts?.gold.toString() } (Gold)"
        locationText.text = user?.location

        // TODO: Age is not available in the API response currently. Can update once available
        ageText.text = "Not Available"

        var formatter = SimpleDateFormat("dd/MM/yyyy")
        creationDateText.text = formatter.format(Date(user?.creation_date!!))
    }
}