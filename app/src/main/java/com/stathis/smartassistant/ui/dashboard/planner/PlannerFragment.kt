package com.stathis.smartassistant.ui.dashboard.planner

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPlannerBinding
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.details.EventInfoActivity
import com.stathis.smartassistant.ui.events.EventsActivity
import com.stathis.smartassistant.util.EVENT
import com.stathis.smartassistant.util.setScreenTitle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlannerFragment : BaseFragment<FragmentPlannerBinding>(R.layout.fragment_planner) {

    private val viewModel: PlannerViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.planner_title))
        binding.viewModel = viewModel
        binding.emptyCalendar = false
    }

    override fun startOps() {
        viewModel.getUserEvents()
        viewModel.notifyAboutPetStatus()

        viewModel.observe(viewLifecycleOwner, showEmptyScreen = { isEmpty ->
            lifecycleScope.launch {
                delay(1000)
                binding.plannerRecycler.scrollToPosition(0)
            }
            binding.emptyCalendar = isEmpty
        })

        viewModel.isUserBusy.observe(viewLifecycleOwner) { isBusy ->
            binding.userIsBusy = isBusy
        }

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