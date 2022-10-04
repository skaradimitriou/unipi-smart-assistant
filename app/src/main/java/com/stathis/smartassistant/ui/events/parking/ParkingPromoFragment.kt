package com.stathis.smartassistant.ui.events.parking

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentParkingPromoBinding
import com.stathis.smartassistant.ui.events.EventsViewModel

class ParkingPromoFragment :
    BaseFragment<FragmentParkingPromoBinding>(R.layout.fragment_parking_promo) {

    private val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        sharedViewModel.screenTitle.value = getString(R.string.events_parking_promo_title)
    }

    override fun startOps() {
        binding.searchForParkingSlotButton.setOnClickListener {
            goToParkingSlotScreen()
        }

        binding.notNowButton.setOnClickListener {
            goToAdditionalsScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the parking slot screen via safeargs
     */

    private fun goToParkingSlotScreen() {
        val action = ParkingPromoFragmentDirections.goToParkingSlotScreen()
        findNavController().navigate(action)
    }

    /*
     * Navigates to the additionals screen via safeargs
     */

    private fun goToAdditionalsScreen() {
        val action = ParkingPromoFragmentDirections.goToAdditionalsScreen()
        findNavController().navigate(action)
    }
}