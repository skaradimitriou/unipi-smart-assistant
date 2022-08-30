package com.stathis.smartassistant.ui.events.transport

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTransportBinding
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.ui.events.traffic.TrafficFragmentDirections

class TransportFragment : BaseFragment<FragmentTransportBinding>(R.layout.fragment_transport) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_transport_title)
    }

    override fun startOps() {
        binding.nextButton.setOnClickListener {
            goToTrafficScreen()
        }
    }

    override fun stopOps() {
        //
    }

    /*
     * Navigates to the traffic screen via safeargs
     */

    private fun goToTrafficScreen() {
        val action = TransportFragmentDirections.goTToTrafficScreen()
        findNavController().navigate(action)
    }
}