package com.stathis.smartassistant.ui.events.parking

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentParkingSlotBinding
import com.stathis.smartassistant.ui.events.EventsViewModel


class ParkingSlotFragment :
    BaseFragment<FragmentParkingSlotBinding>(R.layout.fragment_parking_slot) {

    val viewModel: EventsViewModel by viewModels()

    override fun init() {}

    override fun startOps() {
        binding.continueButton.setOnClickListener {
            viewModel.parkingSlot = binding.parkingSlotTxtView.text.toString()
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