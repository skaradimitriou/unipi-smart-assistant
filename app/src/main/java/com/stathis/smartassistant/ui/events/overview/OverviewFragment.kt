package com.stathis.smartassistant.ui.events.overview

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentOverviewBinding
import com.stathis.smartassistant.ui.events.EventsViewModel

class OverviewFragment : BaseFragment<FragmentOverviewBinding>(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModels()
    private val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun startOps() {
        val fullEventData = sharedViewModel.getEvent()
        viewModel.bindModel(fullEventData)

        binding.continueBtn.setOnClickListener {
            goToResultScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigate to result screen via safeargs
     */

    private fun goToResultScreen() {
        val action = OverviewFragmentDirections.goToResultScreen()
        findNavController().navigate(action)
    }
}