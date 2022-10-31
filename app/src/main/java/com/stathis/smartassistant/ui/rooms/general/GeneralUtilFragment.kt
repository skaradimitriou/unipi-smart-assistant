package com.stathis.smartassistant.ui.rooms.general

import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentGeneralUtilBinding
import com.stathis.smartassistant.models.RoomUtil
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar

class GeneralUtilFragment :
    BaseFragment<FragmentGeneralUtilBinding>(R.layout.fragment_general_util) {

    private val viewModel: GeneralUtilViewModel by viewModels()
    private val args: GeneralUtilFragmentArgs by navArgs()

    override fun init() {
        val title = getScreenTitle(args.util)
        setScreenTitle(title)
        binding.utilImgView.setRoomUtilImage(args.util)
        binding.utilToggle.isChecked = args.util.enabled
        viewModel.enabled = args.util.enabled
    }

    override fun startOps() {
        binding.utilToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.notifyStateChanged(
                room = args.room,
                util = args.util.title,
                state = isChecked
            )
        }

        viewModel.state.observe(viewLifecycleOwner) { isEnabled ->
            if (isEnabled) {
                binding.showSnackbar("Ενεργοποιήθηκε")
            } else {
                binding.showSnackbar("Απενεργοποιήθηκε")
            }
        }

        viewModel.stateSaved.observe(viewLifecycleOwner) { changedSuccessfully ->
            if (!changedSuccessfully) {
                binding.showSnackbar(getString(R.string.issue_occured))
            }
        }
    }

    override fun stopOps() {
        viewModel.state.removeObservers(viewLifecycleOwner)
        viewModel.stateSaved.removeObservers(viewLifecycleOwner)
    }

    private fun ImageView.setRoomUtilImage(util: RoomUtil) {
        val resource = when (util.title) {
            getString(R.string.tv_screen_title) -> R.drawable.television
            getString(R.string.garage_door) -> R.drawable.garage
            getString(R.string.oven) -> R.drawable.oven
            getString(R.string.coffee_machine) -> R.drawable.coffee
            else -> 0
        }
        setImageResource(resource)
    }

    private fun getScreenTitle(util: RoomUtil): String = when (util.title) {
        getString(R.string.tv_screen_title) -> getString(R.string.tv_screen_title)
        getString(R.string.garage_door) -> getString(R.string.garage_door)
        getString(R.string.oven) -> getString(R.string.oven)
        getString(R.string.coffee_machine) -> getString(R.string.coffee_machine)
        else -> ""
    }
}