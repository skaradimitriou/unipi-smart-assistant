package com.stathis.smartassistant.ui.rooms

import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityRoomsBinding
import com.stathis.smartassistant.util.ROOM_NAME

class RoomsActivity : BaseActivity<ActivityRoomsBinding>(R.layout.activity_rooms) {

    private val viewModel : RoomsViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
        binding.selectedRoom = intent.getStringExtra(ROOM_NAME) ?: ""
    }

    override fun startOps() {}

    override fun stopOps() {}
}