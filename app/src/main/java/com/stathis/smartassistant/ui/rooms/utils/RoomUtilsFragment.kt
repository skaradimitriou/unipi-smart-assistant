package com.stathis.smartassistant.ui.rooms.utils

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentRoomUtilsBinding
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar


class RoomUtilsFragment : BaseFragment<FragmentRoomUtilsBinding>(R.layout.fragment_room_utils) {

    private val viewModel: RoomUtilsViewModel by viewModels()
    private val args : RoomUtilsFragmentArgs by navArgs()

    override fun init() {
        setScreenTitle(getString(R.string.room_util_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        val roomUtils = args.room.utils
        viewModel.bindList(roomUtils)
        viewModel.observe(viewLifecycleOwner) { selectedUtil ->
            binding.showSnackbar("clicked on ${selectedUtil.title}")
            //FIXME: Connect other screens later on
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}