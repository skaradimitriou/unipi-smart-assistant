package com.stathis.smartassistant.ui.events.traffic

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTrafficBinding
import com.stathis.smartassistant.ui.events.EventsViewModel

class TrafficFragment : BaseFragment<FragmentTrafficBinding>(R.layout.fragment_traffic) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_traffic_title)
    }

    override fun startOps() {
        val location = viewModel.eventLocation ?: ""
        binding.addressTxtView.text = location

        val transportationType = viewModel.transportationOption?.title
        binding.transportTypeTxtView.text = getString(R.string.transport_via, transportationType)

        binding.nextButton.setOnClickListener {
            goToAdditionalsScreen()
        }
    }

    override fun stopOps() {
        //
    }

    /*
     * Navigates to the additionals screen via safeargs
     */

    private fun goToAdditionalsScreen() {
        //val action = TrafficFragmentDirections.goToAdditionalsScreen()
        //findNavController().navigate(action)
    }
}