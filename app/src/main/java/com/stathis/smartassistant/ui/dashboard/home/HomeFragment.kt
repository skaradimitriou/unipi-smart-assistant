package com.stathis.smartassistant.ui.dashboard.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.callbacks.HomeRoomCallback
import com.stathis.smartassistant.databinding.FragmentRoomsBinding
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.util.setUserGreeting


class HomeFragment : BaseFragment<FragmentRoomsBinding>(R.layout.fragment_rooms) {

    private val viewModel: HomeViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun startOps() {
        binding.userGreetingTxtView.setUserGreeting()

        viewModel.getData(object : HomeRoomCallback {
            override fun onRoomClick(room: Room) {
                //FIXME: Decide what will happen in the next screen
            }
        })
    }

    override fun stopOps() {}
}