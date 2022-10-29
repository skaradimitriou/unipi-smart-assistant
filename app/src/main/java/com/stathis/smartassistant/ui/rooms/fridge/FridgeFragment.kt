package com.stathis.smartassistant.ui.rooms.fridge

import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFridgeBinding
import com.stathis.smartassistant.util.setScreenTitle

class FridgeFragment : BaseFragment<FragmentFridgeBinding>(R.layout.fragment_fridge) {

    override fun init() {
        setScreenTitle(getString(R.string.fridge))
    }

    override fun startOps() {
        binding.primaryButton.setOnClickListener {
            goToFridgeDetailsScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the fridge screen via safeargs
     */

    private fun goToFridgeDetailsScreen() {
        val action = FridgeFragmentDirections.goToFridgeDetailsScreen()
        findNavController().navigate(action)
    }
}