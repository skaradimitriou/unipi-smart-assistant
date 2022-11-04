package com.stathis.smartassistant.ui.rooms.temperature

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTemperatureBinding
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.setTemperatureInCelcius
import com.stathis.smartassistant.util.setTemperatureState
import com.stathis.smartassistant.util.showSnackbar


class TemperatureFragment :
    BaseFragment<FragmentTemperatureBinding>(R.layout.fragment_temperature) {

    private val viewModel: TemperatureViewModel by viewModels()
    private val args: TemperatureFragmentArgs by navArgs()
    private var isActive = false

    override fun init() {
        setScreenTitle(getString(R.string.temperature_screen_title))

        /*
         *  Bind data from firestore database to ui
         */

        binding.temperatureActiveToggle.isChecked = args.util.enabled
        isActive = args.util.enabled
        viewModel.enabled = args.util.enabled
        binding.temperatureActiveTxtView.setTemperatureState(args.util.enabled)

        args.util.heatLevel?.let {
            binding.heatLevelTxtView.setTemperatureInCelcius(it)
            viewModel.heatLevel = it
        }
    }

    override fun startOps() {
        binding.decreaseImgView.setOnClickListener {
            if (isActive) {
                viewModel.decreaseHeat(args.room)
            } else {
                binding.showSnackbar(getString(R.string.activate_aircondition_msg))
            }
        }

        binding.increaseImgView.setOnClickListener {
            if (isActive) {
                viewModel.increaseHeat(args.room)
            } else {
                binding.showSnackbar(getString(R.string.activate_aircondition_msg))
            }
        }

        binding.temperatureActiveToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            isActive = isChecked
            viewModel.notifyTemperatureActivated(room = args.room, isEnabled = isChecked)
            binding.temperatureActiveTxtView.setTemperatureState(isChecked)
        }

        viewModel.heat.observe(viewLifecycleOwner) { heatLevel ->
            binding.heatLevelTxtView.setTemperatureInCelcius(heatLevel)
        }

        viewModel.maxHeatReached.observe(viewLifecycleOwner) { endReached ->
            if (endReached) {
                binding.showSnackbar(getString(R.string.max_heat_reached))
            }
        }

        viewModel.minHeatReached.observe(viewLifecycleOwner) { endReached ->
            if (endReached) {
                binding.showSnackbar(getString(R.string.min_heat_reached))
            }
        }
    }

    override fun stopOps() {
        viewModel.heatLevel
    }
}