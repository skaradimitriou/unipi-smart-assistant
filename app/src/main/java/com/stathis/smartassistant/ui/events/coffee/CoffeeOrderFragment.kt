package com.stathis.smartassistant.ui.events.coffee

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentCoffeeOrderBinding
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.playOnceAndStop
import com.stathis.smartassistant.util.showSugarSelection
import com.stathis.smartassistant.util.showWalletDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                    binding.verifyingPayment = true

                    //creates dummy payment verification & success effect on UI
                    lifecycleScope.launch {
                        delay(3000)
                        binding.paymentLottie.playOnceAndStop(
                            animation = R.raw.payment_success,
                            onEnd = {
                                goToOverviewScreen()
                                binding.verifyingPayment = false
                            })
                    }
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