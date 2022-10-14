package com.stathis.smartassistant.ui.rooms.temperature

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentTemperatureBinding


class TemperatureFragment : BaseFragment<FragmentTemperatureBinding>(R.layout.fragment_temperature) {

    private val viewModel: TemperatureViewModel by viewModels()

    override fun init() {
        //
    }

    override fun startOps() {
        //
    }

    override fun stopOps() {
        //
    }
}