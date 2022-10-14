package com.stathis.smartassistant.ui.rooms

import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityRoomsBinding

class RoomsActivity : BaseActivity<ActivityRoomsBinding>(R.layout.activity_rooms) {

    private val viewModel: RoomsViewModel by viewModels()

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