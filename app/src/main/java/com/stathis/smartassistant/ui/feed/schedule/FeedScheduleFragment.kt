package com.stathis.smartassistant.ui.feed.schedule

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFeedScheduleBinding
import com.stathis.smartassistant.models.feeding.FeedingType
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showDatePicker
import com.stathis.smartassistant.util.showTimePicker
import com.stathis.smartassistant.util.showYesNoDialog


class FeedScheduleFragment :
    BaseFragment<FragmentFeedScheduleBinding>(R.layout.fragment_feed_schedule) {

    private val activityViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.feed_planner_title))
    }

    override fun startOps() {
        binding.feedNowBtn.setOnClickListener {
            goToResultScreen()
        }

        binding.feedLaterBtn.setOnClickListener {
            showDatePicker { dateSelected ->
                showTimePicker { timeSelected ->

                    val text = when (activityViewModel.feedType) {
                        FeedingType.WATER_NOW -> getString(R.string.put_water)
                        else -> getString(R.string.put_food)
                    }

                    showYesNoDialog(
                        message = "Είστε σίγουρος/η οτι θα θέλατε να $text το κατοικίδιο σας στις $dateSelected και ώρα $timeSelected;",
                        onButtonClick = {
                            when (activityViewModel.feedType) {
                                FeedingType.WATER_NOW -> activityViewModel.feedType =
                                    FeedingType.WATER_LATER
                                FeedingType.FOOD_NOW -> activityViewModel.feedType =
                                    FeedingType.FOOD_LATER
                                else -> Unit
                            }

                            goToResultScreen()
                        }
                    )
                }
            }
        }
    }

    override fun stopOps() {}

    /*
     * Navigate to the result screen of the feeding flow
     */

    private fun goToResultScreen() {
        val action = FeedScheduleFragmentDirections.goToResultScreen()
        findNavController().navigate(action)
    }
}