package com.stathis.smartassistant.ui.events.coffee

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentCoffeeOrderBinding
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.showSugarSelection
import com.stathis.smartassistant.util.showWalletDialog

class CoffeeOrderFragment :
    BaseFragment<FragmentCoffeeOrderBinding>(R.layout.fragment_coffee_order) {

    private val viewModel: CoffeeOrderViewModel by viewModels()
    private val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        binding.viewModel = viewModel
        sharedViewModel.screenTitle.value = getString(R.string.events_coffee_title)
    }

    override fun startOps() {
        viewModel.bindList { selectedCoffee ->
            sharedViewModel.selectedCoffee = selectedCoffee

            //shows sugar selection dialog
            showSugarSelection(selectedCoffee, sugarType = { sugarType ->
                sharedViewModel.selectedCoffee?.sugar = sugarType

                //shows demo e-wallet dialog & proceeds to overview screen on payment button click
                showWalletDialog {
                    goToOverviewScreen()
                }
            })
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the overview screen via safeargs
     */

    private fun goToOverviewScreen() {
        val action = CoffeeOrderFragmentDirections.goToOverviewScreen()
        findNavController().navigate(action)
    }
}