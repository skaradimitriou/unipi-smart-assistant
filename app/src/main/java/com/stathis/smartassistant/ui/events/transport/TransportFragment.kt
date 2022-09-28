package com.stathis.smartassistant.ui.events.transport

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTransportBinding
import com.stathis.smartassistant.models.TransportationOption
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
            decideNextScreen(selectedOption)
        }
    }

    override fun stopOps() {}

    private fun decideNextScreen(option: TransportationOption) = when (option.title) {
        getString(R.string.transport_car) -> goToParkingScreen()
        else -> goToAdditionalsScreen()
    }

    /*
     * Navigates to the parking screen via safeargs
     */

    private fun goToParkingScreen() {
        val action = TransportFragmentDirections.goToParkingPromoScreen()
        findNavController().navigate(action)
    }

    /*
     * Navigates to the additionals screen via safeargs
     */

    private fun goToAdditionalsScreen() {
        val action = TransportFragmentDirections.goToAdditionalsScreen()
        findNavController().navigate(action)
    }
}