package com.stathis.smartassistant.ui.wardrobe.events

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentEventsBinding
import com.stathis.smartassistant.ui.wardrobe.WardrobeViewModel
import com.stathis.smartassistant.util.setScreenTitle


class EventsFragment : BaseFragment<FragmentEventsBinding>(R.layout.fragment_events) {

    private val viewModel: EventsViewModel by viewModels()
    private val sharedViewModel: WardrobeViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_events_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.getUserEvents()
        viewModel.observe(viewLifecycleOwner, { isEmpty ->
            binding.emptyCalendar = isEmpty
        })

        viewModel.onEventTap { event ->
            sharedViewModel.event = event
            goToCalendarScreen()
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Navigates to the calendar screen via safeargs
     */

    private fun goToCalendarScreen() {
        val action = EventsFragmentDirections.goToCategoryScreen()
        findNavController().navigate(action)
    }
}