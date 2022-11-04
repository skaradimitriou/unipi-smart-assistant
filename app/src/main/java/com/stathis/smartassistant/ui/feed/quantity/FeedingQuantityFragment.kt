package com.stathis.smartassistant.ui.feed.quantity

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentFeedingQuantityBinding
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle

class FeedingQuantityFragment :
    BaseFragment<FragmentFeedingQuantityBinding>(R.layout.fragment_feeding_quantity) {

    private val sharedViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle("Επιλογή Ποσότητας")
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}