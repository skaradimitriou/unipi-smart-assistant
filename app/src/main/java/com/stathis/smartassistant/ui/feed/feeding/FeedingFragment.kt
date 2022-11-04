package com.stathis.smartassistant.ui.feed.feeding

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFeedingBinding
import com.stathis.smartassistant.models.feeding.FeedingType
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle


class FeedingFragment : BaseFragment<FragmentFeedingBinding>(R.layout.fragment_feeding) {

    private val sharedViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.feeding_type_title))
    }

    override fun startOps() {
        binding.foodCardView.setOnClickListener {
            decideNextScreen(type = FeedingType.FOOD)
        }

        binding.waterCardView.setOnClickListener {
            decideNextScreen(type = FeedingType.WATER)
        }
    }

    override fun stopOps() {}

    private fun decideNextScreen(type: FeedingType) {
        sharedViewModel.feedType = type
        goToQuantityScreen()
    }

    /*
     * Navigates to feeding quantity screen via safeargs
     */

    private fun goToQuantityScreen() {
        val action = FeedingFragmentDirections.goToQuantityScreen()
        findNavController().navigate(action)
    }
}