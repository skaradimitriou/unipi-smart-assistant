package com.stathis.smartassistant.ui.rooms

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityRoomsBinding

class RoomsActivity : BaseActivity<ActivityRoomsBinding>(R.layout.activity_rooms) {

    private val viewModel: RoomsViewModel by viewModels()
    private lateinit var navController: NavController

    override fun init() {
        navController = findNavController(R.id.rooms_nav_host_fragment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        //
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