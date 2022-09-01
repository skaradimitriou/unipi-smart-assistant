package com.stathis.smartassistant.ui.events


import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityEventsBinding

class EventsActivity : BaseActivity<ActivityEventsBinding>(R.layout.activity_events) {

    private lateinit var navController: NavController
    private val viewModel: EventsViewModel by viewModels()

    override fun init() {
        navController = findNavController(R.id.events_nav_host_fragment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        viewModel.screenTitle.observe(this) { title ->
            supportActionBar?.title = title
        }
    }

    override fun stopOps() {}

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