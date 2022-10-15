package com.stathis.smartassistant.ui.rooms.intro

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentRoomsIntroBinding
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
            binding.showSnackbar("Clicked on ${selectedRoom.title}")
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}