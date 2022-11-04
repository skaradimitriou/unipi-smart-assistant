package com.stathis.smartassistant.ui.events.traffic

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTrafficBinding
import com.stathis.smartassistant.models.TransportationOption
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.setRoutePhoto
import com.stathis.smartassistant.util.toUiModel

class TrafficFragment : BaseFragment<FragmentTrafficBinding>(R.layout.fragment_traffic) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        binding.viewModel = viewModel
        viewModel.screenTitle.value = getString(R.string.events_traffic_title)
    }

    override fun startOps() {
        val option = viewModel.transportationOption
        option?.let {
            binding.trafficRouteImgView.setRoutePhoto(it)
        }

        binding.nextButton.setOnClickListener {
            val transportOption = viewModel.transportationOption.toUiModel()
            decideNextScreen(transportOption)
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
        val action = TrafficFragmentDirections.goToParkingPromoScreen()
        findNavController().navigate(action)
    }

    /*
     * Navigates to the additionals screen via safeargs
     */

    private fun goToAdditionalsScreen() {
        val action = TrafficFragmentDirections.goToAdditionalsScreen()
        findNavController().navigate(action)
    }
}