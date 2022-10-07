package com.stathis.smartassistant.ui.events.overview

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentOverviewBinding
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.showSnackbar
import timber.log.Timber

class OverviewFragment : BaseFragment<FragmentOverviewBinding>(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModels()
    private val sharedViewModel: EventsViewModel by activityViewModels()

    override fun init() {
        binding.viewModel = viewModel
        sharedViewModel.screenTitle.value = getString(R.string.events_overview_title)
    }

    override fun startOps() {
        val fullEventData = sharedViewModel.getEvent()
        viewModel.bindModel(fullEventData)

        binding.continueBtn.setOnClickListener {
            sharedViewModel.saveEventToDatabase()
        }

        sharedViewModel.eventSaved.observe(viewLifecycleOwner) { savedSuccessfully ->
            if (savedSuccessfully) {
                goToResultScreen()
            } else {
                binding.showSnackbar(getString(R.string.save_event_error_msg))
                Timber.e("Failed")
            }
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