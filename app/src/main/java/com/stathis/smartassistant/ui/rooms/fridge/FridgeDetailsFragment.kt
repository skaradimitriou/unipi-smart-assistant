package com.stathis.smartassistant.ui.rooms.fridge

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFridgeDetailsBinding
import com.stathis.smartassistant.util.setScreenTitle

class FridgeDetailsFragment :
    BaseFragment<FragmentFridgeDetailsBinding>(R.layout.fragment_fridge_details) {

    private val viewModel: FridgeDetailsViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.fridge_details_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner)
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}