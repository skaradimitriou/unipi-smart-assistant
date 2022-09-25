package com.stathis.smartassistant.ui.events.store

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentCoffeeShopsBinding
import com.stathis.smartassistant.ui.events.EventsViewModel


class CoffeeShopsFragment :
    BaseFragment<FragmentCoffeeShopsBinding>(R.layout.fragment_coffee_shops) {

    val viewModel: CoffeeShopsViewModel by viewModels()
    val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        sharedViewModel.screenTitle.value = getString(R.string.events_shops_title)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.bindList { selectedShop ->
            sharedViewModel.selectedShop = selectedShop
            goToCoffeeDetails()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the cofee details screen via safeargs
     */

    private fun goToCoffeeDetails() {
        val action = CoffeeShopsFragmentDirections.goToCoffeeOrderScreen()
        findNavController().navigate(action)
    }
}