package com.stathis.smartassistant.ui.rooms.lights

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentLightsManagementBinding
import com.stathis.smartassistant.util.setLightState
import com.stathis.smartassistant.util.showSnackbar

class LightsManagementFragment :
    BaseFragment<FragmentLightsManagementBinding>(R.layout.fragment_lights_management) {

    private val viewModel: LightsManagementViewModel by viewModels()
    private val args: LightsManagementFragmentArgs by navArgs()

    override fun init() {
        binding.lightsActiveToggle.isChecked = args.util.enabled
        viewModel.enabled = args.util.enabled
        binding.lightBulbImgView.setLightState(args.util.enabled)
    }

    override fun startOps() {
        binding.lightsActiveToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.notifyLightsModeChanged(room = args.room, lightState = isChecked)
        }

        viewModel.lightsState.observe(viewLifecycleOwner) { lightsAreOn ->
            binding.lightBulbImgView.setLightState(lightsAreOn)
            if (lightsAreOn) {
                binding.showSnackbar(getString(R.string.lights_on_msg))
            } else {
                binding.showSnackbar(getString(R.string.lights_out_msg))
            }
        }

        viewModel.lightsStateSaved.observe(viewLifecycleOwner) { changedSuccessfully ->
            if (!changedSuccessfully) {
                binding.showSnackbar(getString(R.string.issue_occured))
            }
        }
    }

    override fun stopOps() {
        viewModel.lightsState.removeObservers(viewLifecycleOwner)
        viewModel.lightsStateSaved.removeObservers(viewLifecycleOwner)
    }
}