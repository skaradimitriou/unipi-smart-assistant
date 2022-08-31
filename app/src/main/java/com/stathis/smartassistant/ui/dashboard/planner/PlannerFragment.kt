package com.stathis.smartassistant.ui.dashboard.planner

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPlannerBinding
import com.stathis.smartassistant.ui.events.EventsActivity
import com.stathis.smartassistant.util.addMenuProvider
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showAlertDialog
import com.stathis.smartassistant.util.showSnackbar

class PlannerFragment : BaseFragment<FragmentPlannerBinding>(R.layout.fragment_planner),
    MenuProvider {

    private val viewModel: PlannerViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.planner_title))
        addMenuProvider(this)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.onEventTap { event ->
            //
        }
    }

    override fun stopOps() {}

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.planner_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.infoItem -> showAlertDialog(getString(R.string.planner_info))
            R.id.addItem -> startActivity(Intent(requireContext(), EventsActivity::class.java))
        }
        return false
    }
}