package com.stathis.smartassistant.ui.dashboard.notifications

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentNotificationsBinding
import com.stathis.smartassistant.ui.dashboard.DashboardViewModel
import com.stathis.smartassistant.util.setScreenTitle

class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding>(R.layout.fragment_notifications) {

    private val viewModel: DashboardViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.nav_notifications))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        //FIXME: Figure out a way to read-unread notifications in this screen

        viewModel.observe(viewLifecycleOwner)
    }

    override fun stopOps() {}
}