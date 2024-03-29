package com.stathis.smartassistant.ui.feed.quantity

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFeedingQuantityBinding
import com.stathis.smartassistant.models.feeding.FeedingType
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showTimePicker

class FeedingQuantityFragment :
    BaseFragment<FragmentFeedingQuantityBinding>(R.layout.fragment_feeding_quantity) {

    private val viewModel: FeedingQuantityViewModel by viewModels()
    private val sharedViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.feed_quantity_title))
        binding.userWillFeedPet = sharedViewModel.feedType == FeedingType.FOOD_NOW

        if (sharedViewModel.feedType == FeedingType.WATER_NOW) {
            binding.quantityHeaderTxtView.text = getString(R.string.water)
            binding.quantityDescTxtView.text = getString(R.string.fill_water_bowl)
        }
    }

    override fun startOps() {
        binding.increaseImgView.setOnClickListener {
            viewModel.increase()
        }

        binding.decreaseImgView.setOnClickListener {
            viewModel.decrease()
        }

        binding.nextButton.setOnClickListener {
            goToScheduleScreen()
        }

        viewModel.counter.observe(viewLifecycleOwner) { counter ->
            binding.quantityLevelTxtView.text = "$counter γρ"
        }
    }

    override fun stopOps() {

    }

    /*
     * Navigate to the schedule screen of the feeding flow
     */

    private fun goToScheduleScreen() {
        val action = FeedingQuantityFragmentDirections.goToFeedScheduleScreen()
        findNavController().navigate(action)
    }
}