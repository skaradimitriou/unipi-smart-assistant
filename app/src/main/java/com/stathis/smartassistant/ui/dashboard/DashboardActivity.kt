package com.stathis.smartassistant.ui.dashboard

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityDashboardBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {

    private lateinit var navController: NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationMenu.setupWithNavController(navController)
    }

    override fun startOps() {}

    override fun stopOps() {}
}