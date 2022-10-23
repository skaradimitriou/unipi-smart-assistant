package com.stathis.smartassistant.ui.dashboard

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityDashboardBinding
import com.stathis.smartassistant.ui.events.EventsActivity
import com.stathis.smartassistant.ui.faq.FaqActivity
import com.stathis.smartassistant.ui.rooms.RoomsActivity
import com.stathis.smartassistant.ui.wardrobe.WardrobeActivity
import timber.log.Timber

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var toggle: ActionBarDrawerToggle

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationMenu.setupWithNavController(navController)

        //setup drawer menu
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun startOps() {
        binding.drawerMenu.setNavigationItemSelectedListener(this)
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (binding.drawerLayout.isOpen) {
            true -> binding.drawerLayout.closeDrawer(GravityCompat.START)
            false -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_rooms -> goToRoomsScreen()
            R.id.nav_smart_wardrobe -> goToSmartWardrobeScreen()
            R.id.nav_new_event -> goToNewEventScreen()
            R.id.nav_faq -> goToFaqScreen()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*
     * Starts the Rooms flow of the app
     */

    private fun goToRoomsScreen() {
        val intent = Intent(this, RoomsActivity::class.java)
        startActivity(intent)
    }

    /*
     * Starts the SmartWardrobe activity in the flow
     */

    private fun goToSmartWardrobeScreen() {
        val intent = Intent(this, WardrobeActivity::class.java)
        startActivity(intent)
    }

    /*
     * Starts the FAQ activity in the flow
     */

    private fun goToFaqScreen() {
        val intent = Intent(this, FaqActivity::class.java)
        startActivity(intent)
    }

    private fun goToNewEventScreen() {
        val intent = Intent(this, EventsActivity::class.java)
        startActivityForResult(intent, 100)
    }
}