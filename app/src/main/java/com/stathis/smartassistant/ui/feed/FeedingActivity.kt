package com.stathis.smartassistant.ui.feed

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.ActivityFeedingBinding

class FeedingActivity : BaseFragment<ActivityFeedingBinding>(R.layout.activity_feeding) {

    private val viewModel: FeedingViewModel by viewModels()

    override fun init() {
        //
    }

    override fun startOps() {
        //
    }

    override fun stopOps() {
        //
    }
}