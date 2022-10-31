package com.stathis.smartassistant.ui.rooms.utils

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentRoomUtilsBinding
import com.stathis.smartassistant.models.RoomUtil
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar


class RoomUtilsFragment : BaseFragment<FragmentRoomUtilsBinding>(R.layout.fragment_room_utils) {

    private val viewModel: RoomUtilsViewModel by viewModels()
    private val args: RoomUtilsFragmentArgs by navArgs()

    override fun init() {
        setScreenTitle(getString(R.string.room_util_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        val roomUtils = args.room.utils
        viewModel.bindList(roomUtils)
        viewModel.observe(viewLifecycleOwner) { selectedUtil ->
            decideNextAction(selectedUtil)
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    private fun decideNextAction(util: RoomUtil) = when (util.title) {
        getString(R.string.lights_screen_title) -> goToLightManagementScreen(util)
        getString(R.string.temperature_screen_title) -> goToTemperatureScreen(util)
        getString(R.string.music) -> goToHomepodScreen(util)
        getString(R.string.fridge) -> goToFridgeScreen()
        else -> goToGeneralUtilScreen(util)
    }

    /*
     * Navigates to the light management screen via safeargs
     */

    private fun goToLightManagementScreen(util: RoomUtil) {
        val action = RoomUtilsFragmentDirections.goToLightsManagementScreen(util, args.room)
        findNavController().navigate(action)
    }

    /*
     * Navigates to the temperature screen via safeargs
     */

    private fun goToTemperatureScreen(util: RoomUtil) {
        val action = RoomUtilsFragmentDirections.goToTemperatureScreen(util, args.room)
        findNavController().navigate(action)
    }

    /*
     * Navigates to the homepod screen via safeargs
     */

    private fun goToHomepodScreen(util: RoomUtil) {
        val action = RoomUtilsFragmentDirections.goToHomepodScreen(util, args.room)
        findNavController().navigate(action)
    }

    /*
     * Navigates to the fridge screen via safeargs
     */

    private fun goToFridgeScreen() {
        val action = RoomUtilsFragmentDirections.goToFridgeScreen()
        findNavController().navigate(action)
    }

    /*
     * Navigates to the general util screen via safeargs
     */

    private fun goToGeneralUtilScreen(util: RoomUtil) {
        val action = RoomUtilsFragmentDirections.goToGeneralUtilScreen(util, args.room)
        findNavController().navigate(action)
    }
}