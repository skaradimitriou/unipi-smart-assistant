package com.stathis.smartassistant.ui.events.intro

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentIntroBinding
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.afterTextChanged


class IntroFragment : BaseFragment<FragmentIntroBinding>(R.layout.fragment_intro) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_intro_title)
    }

    override fun startOps() {
        binding.eventDescriptionEditTxt.afterTextChanged { input ->
            validateInput(input)
        }

        binding.nextButton.setOnClickListener {
            binding.ctaEnabled = false
            goToTransportScreen()
        }
    }

    override fun stopOps() {}

    private fun validateInput(input: String) {
        if (input.isNotEmpty() && input.length >= 3) {
            viewModel.eventTitle = input
            binding.ctaEnabled = true
            binding.eventDescInputField.error = null
        } else {
            binding.ctaEnabled = false
            binding.eventDescInputField.error = getString(R.string.events_error_msg)
        }
    }

    /*
     * Navigates to the transport screen via safeargs
     */

    private fun goToTransportScreen() {
        val action = IntroFragmentDirections.goToTransportationsScreen()
        findNavController().navigate(action)
    }
}