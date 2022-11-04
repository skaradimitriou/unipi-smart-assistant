package com.stathis.smartassistant.ui.feed.quantity

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFeedingQuantityBinding
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle

class FeedingQuantityFragment :
    BaseFragment<FragmentFeedingQuantityBinding>(R.layout.fragment_feeding_quantity) {

    private val sharedViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.feed_quantity_title))
    }

    override fun startOps() {
        binding.nextButton.setOnClickListener {
            goToResultScreen()
        }
    }

    override fun stopOps() {

    }

    /*
     * Navigate to the result screen of the feeding flow
     */

    private fun goToResultScreen() {
        val action = FeedingQuantityFragmentDirections.goToResultScreen()
        findNavController().navigate(action)
    }
}