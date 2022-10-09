package com.stathis.smartassistant.ui.wardrobe.shoes

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentShoesBinding
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar

class ShoesFragment : BaseFragment<FragmentShoesBinding>(R.layout.fragment_shoes) {

    private val viewModel: ShoesViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_choose_shoes))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner, callback = {
            binding.showSnackbar("Clicked ${it.title}")
        })
        viewModel.getShoes()
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}