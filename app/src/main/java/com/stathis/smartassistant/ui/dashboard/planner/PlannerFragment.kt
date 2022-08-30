package com.stathis.smartassistant.ui.dashboard.planner

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPlannerBinding
import com.stathis.smartassistant.ui.events.EventsActivity

class PlannerFragment : BaseFragment<FragmentPlannerBinding>(R.layout.fragment_planner) {

    private val viewModel : PlannerViewModel by viewModels()

    override fun init() {

    }

    override fun startOps() {
        binding.newEventBtn.setOnClickListener {
            startActivity(Intent(requireContext(), EventsActivity::class.java))
        }
    }

    override fun stopOps() {

    }
}