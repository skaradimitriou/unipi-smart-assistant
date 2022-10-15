package com.stathis.smartassistant.ui.rooms.intro

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentRoomsIntroBinding
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.ui.events.intro.IntroFragmentDirections
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar

class RoomsIntroFragment : BaseFragment<FragmentRoomsIntroBinding>(R.layout.fragment_rooms_intro) {

    private val viewModel: RoomsIntroViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.room_into_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.getRooms()
        viewModel.observe(viewLifecycleOwner) { selectedRoom ->
            goToRoomUtilsScreen(selectedRoom)
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Navigates to the room utils screen via safeargs
     */

    private fun goToRoomUtilsScreen(selectedRoom : Room) {
        val action = RoomsIntroFragmentDirections.goToRoomUtilsScreen(selectedRoom)
        findNavController().navigate(action)
    }
}