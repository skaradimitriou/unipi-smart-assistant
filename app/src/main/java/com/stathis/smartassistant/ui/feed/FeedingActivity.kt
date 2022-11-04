package com.stathis.smartassistant.ui.feed

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityFeedingBinding

class FeedingActivity : BaseActivity<ActivityFeedingBinding>(R.layout.activity_feeding) {

    private val viewModel: FeedingViewModel by viewModels()

    private lateinit var navController: NavController

    override fun init() {
        navController = findNavController(R.id.feeding_nav_host_fragment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.petResultFragment) {
                //disable action bar back button
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun stopOps() {
        //
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            if (navController.graph.startDestinationId == navController.currentDestination?.id) {
                finish()
            } else {
                navController.popBackStack()
            }
            true
        }
        else -> false
    }
}