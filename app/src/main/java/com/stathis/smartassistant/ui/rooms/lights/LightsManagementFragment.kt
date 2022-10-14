package com.stathis.smartassistant.ui.rooms.lights

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentLightsManagementBinding

class LightsManagementFragment :
    BaseFragment<FragmentLightsManagementBinding>(R.layout.fragment_lights_management) {

    private val viewModel: LightsManagementViewModel by viewModels()

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