package com.stathis.smartassistant.ui.wardrobe.eshops

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentEshopsBinding
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar

class EshopsFragment : BaseFragment<FragmentEshopsBinding>(R.layout.fragment_eshops) {

    private val viewModel: EshopsViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_eshops_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner) { selectedEshop ->
            binding.showSnackbar("ESHOP CLICKED")
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}