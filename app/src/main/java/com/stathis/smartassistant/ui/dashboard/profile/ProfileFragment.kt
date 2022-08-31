package com.stathis.smartassistant.ui.dashboard.profile

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentProfileBinding
import com.stathis.smartassistant.util.setScreenTitle

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.profile_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.registerCallback {
            //FIXME: Handle profile option clicks
        }
    }

    override fun stopOps() {}
}