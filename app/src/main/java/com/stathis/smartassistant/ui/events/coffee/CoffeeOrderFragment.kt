package com.stathis.smartassistant.ui.events.coffee

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.CoffeeSugarSelectionBsBinding
import com.stathis.smartassistant.databinding.FragmentCoffeeOrderBinding
import com.stathis.smartassistant.models.SugarType
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.showSugarSelection
import timber.log.Timber

class CoffeeOrderFragment :
    BaseFragment<FragmentCoffeeOrderBinding>(R.layout.fragment_coffee_order) {

    private val viewModel: CoffeeOrderViewModel by viewModels()
    private val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        binding.viewModel = viewModel
        sharedViewModel.screenTitle.value = getString(R.string.events_shops_title)
    }

    override fun startOps() {
        viewModel.bindList { selectedCoffee ->
            sharedViewModel.selectedCoffee = selectedCoffee
            showSugarSelection { sugarType ->
                sharedViewModel.selectedCoffee?.sugar = sugarType
                goToOverviewScreen()
            }
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