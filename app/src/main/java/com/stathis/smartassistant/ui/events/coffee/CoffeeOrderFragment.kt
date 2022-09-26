package com.stathis.smartassistant.ui.events.coffee

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentCoffeeOrderBinding
import com.stathis.smartassistant.ui.events.EventsViewModel

class CoffeeOrderFragment : BaseFragment<FragmentCoffeeOrderBinding>(R.layout.fragment_coffee_order) {

    val viewModel: CoffeeOrderViewModel by viewModels()
    val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        binding.viewModel = viewModel
        sharedViewModel.screenTitle.value = getString(R.string.events_shops_title)
    }

    override fun startOps() {
        viewModel.bindList { selectedCoffee ->
            sharedViewModel.selectedCoffee = selectedCoffee
            goToOverviewScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the overview screen via safeargs
     */

    private fun goToOverviewScreen() {
        //FIXME: Add functionality
    }
}