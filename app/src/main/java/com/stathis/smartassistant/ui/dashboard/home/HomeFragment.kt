package com.stathis.smartassistant.ui.dashboard.home

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentRoomsBinding


class HomeFragment : BaseFragment<FragmentRoomsBinding>(R.layout.fragment_rooms) {

    private val viewModel : HomeViewModel by viewModels()

    override fun init() {
        viewModel.getData()
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner)
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}