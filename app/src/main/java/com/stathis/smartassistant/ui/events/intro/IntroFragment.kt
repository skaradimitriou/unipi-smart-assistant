package com.stathis.smartassistant.ui.events.intro

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentIntroBinding
import com.stathis.smartassistant.ui.events.EventsViewModel


class IntroFragment : BaseFragment<FragmentIntroBinding>(R.layout.fragment_intro) {

    private val viewModel : EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_intro_title)
    }

    override fun startOps() {
        binding.nextButton.setOnClickListener {
            goToTransportScreen()
        }
    }

    override fun stopOps() {
        //
    }

    /*
     * Navigates to the transport screen via safeargs
     */

    private fun goToTransportScreen() {
        val action = IntroFragmentDirections.goToTransportationsScreen()
        findNavController().navigate(action)
    }
}