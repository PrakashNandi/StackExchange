package com.candyspace.stackexchange.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.candyspace.stackexchange.R
import com.candyspace.stackexchange.interfaces.ToolbarSetter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Prakash Nandi on 30/01/21.
 */
class MainActivity : AppCompatActivity(), ToolbarSetter, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backImage.setOnClickListener(this)
    }

    /**
     * to set title for the screen
     */
    override fun setToolbarTitle(titleText: String) {
        toolbarTitle.text = titleText
    }

    /**
     * To show or hide back button.
     */
    override fun showBackButton(show: Boolean) {
        if (show) {
            backImage.visibility = View.VISIBLE
        } else {
            backImage.visibility = View.GONE
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.backImage -> {
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.popBackStack()
            }
        }
    }
}