package com.stathis.smartassistant.ui.events.parking

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentParkingSlotBinding
import com.stathis.smartassistant.models.ParkingInfo
import com.stathis.smartassistant.ui.events.EventsViewModel


class ParkingSlotFragment :
    BaseFragment<FragmentParkingSlotBinding>(R.layout.fragment_parking_slot) {

    val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_parking_slot_title)
    }

    override fun startOps() {
        binding.continueButton.setOnClickListener {
            val parkingInfo = ParkingInfo(
                slot = binding.parkingSlotTxtView.text.toString(),
                company = binding.parkingCompanyTxtView.text.toString()
            )
            viewModel.parkingData = parkingInfo
            goToAdditionalsScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the additionals screen via safeargs
     */

    private fun goToAdditionalsScreen() {
        val action = ParkingSlotFragmentDirections.goToAdditionalsScreen()
        findNavController().navigate(action)
    }
}