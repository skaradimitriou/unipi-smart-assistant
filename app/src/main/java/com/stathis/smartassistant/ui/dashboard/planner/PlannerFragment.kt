package com.stathis.smartassistant.ui.dashboard.planner

import android.content.Intent
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPlannerBinding
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.details.EventInfoActivity
import com.stathis.smartassistant.ui.events.EventsActivity
import com.stathis.smartassistant.util.EVENT
import com.stathis.smartassistant.util.setScreenTitle

class PlannerFragment : BaseFragment<FragmentPlannerBinding>(R.layout.fragment_planner) {

    private val viewModel: PlannerViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.planner_title))
        binding.viewModel = viewModel
        binding.emptyCalendar = false
    }

    override fun startOps() {
        viewModel.getUserEvents()
        viewModel.observe(viewLifecycleOwner, showEmptyScreen = { isEmpty ->
            binding.emptyCalendar = isEmpty
        })

        viewModel.onEventTap { event ->
            goToEventInfoScreen(event)
        }

        binding.newEventFabBtn.setOnClickListener {
            val intent = Intent(requireContext(), EventsActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    private fun goToEventInfoScreen(event: Event) {
        val json = Gson().toJson(event)
        val intent = Intent(requireContext(), EventInfoActivity::class.java).apply {
            putExtra(EVENT, json)
        }
        startActivity(intent)
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}