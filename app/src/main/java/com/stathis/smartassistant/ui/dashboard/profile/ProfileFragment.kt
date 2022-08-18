package com.stathis.smartassistant.ui.dashboard.profile

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel : ProfileViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.registerCallback {
            //FIXME: Handle profile option clicks
        }
    }

    override fun stopOps() {}
}