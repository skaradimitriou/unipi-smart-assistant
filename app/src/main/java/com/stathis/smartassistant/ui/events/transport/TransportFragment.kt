package com.stathis.smartassistant.ui.events.transport

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTransportBinding
import com.stathis.smartassistant.ui.events.EventsViewModel

class TransportFragment : BaseFragment<FragmentTransportBinding>(R.layout.fragment_transport) {

    private val sharedViewModel: EventsViewModel by activityViewModels()
    private val viewModel: TransportViewModel by viewModels()

    override fun init() {
        sharedViewModel.screenTitle.value = getString(R.string.events_transport_title)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.onTransportOptionTap { selectedOption ->
            sharedViewModel.transportationOption = selectedOption
            goToTrafficScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the traffic screen via safeargs
     */

    private fun goToTrafficScreen() {
        val action = TransportFragmentDirections.goToTrafficScreen()
        findNavController().navigate(action)
    }
}