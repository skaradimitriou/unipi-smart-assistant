package com.stathis.smartassistant.ui.events.additionals

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentAdditionalsBinding
import com.stathis.smartassistant.ui.events.EventsViewModel


class AdditionalsFragment : BaseFragment<FragmentAdditionalsBinding>(R.layout.fragment_additionals) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_additionals_title)
    }

    override fun startOps() {
        binding.pickStoreButton.setOnClickListener {
            goToShopSelection()
        }

        binding.notNowButton.setOnClickListener {
            goToEventOverview()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the result screen via safeargs
     */

    private fun goToShopSelection() {
        val action = AdditionalsFragmentDirections.goToShopsScreen()
        findNavController().navigate(action)
    }

    /*
     * Navigates to the overview screen via safeargs
     */

    private fun goToEventOverview() {
        val action = AdditionalsFragmentDirections.goToOverviewScreen()
        findNavController().navigate(action)
    }
}