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
        binding.nextButton.setOnClickListener {
            goToResultScreen()
        }
    }

    override fun stopOps() {
        //
    }

    /*
     * Navigates to the result screen via safeargs
     */

    private fun goToResultScreen() {
        val action = AdditionalsFragmentDirections.goToResultScreen()
        findNavController().navigate(action)
    }
}