package com.stathis.smartassistant.ui.dashboard.home

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentRoomsBinding
import com.stathis.smartassistant.ui.rooms.RoomsActivity
import com.stathis.smartassistant.util.ROOM_NAME
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.setUserGreeting


class HomeFragment : BaseFragment<FragmentRoomsBinding>(R.layout.fragment_rooms) {

    private val viewModel: HomeViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.home_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        binding.userGreetingTxtView.setUserGreeting()

        viewModel.getData { room ->
            goToRoomUtilsScreen(roomName = room.title)
        }
    }

    override fun stopOps() {}

    private fun goToRoomUtilsScreen(roomName: String) {
        val action = Intent(requireContext(), RoomsActivity::class.java).apply {
            putExtra(ROOM_NAME, roomName)
        }
        startActivity(action)
    }
}