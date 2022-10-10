package com.stathis.smartassistant.ui.wardrobe.intro

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentWardrobeIntroBinding
import com.stathis.smartassistant.ui.wardrobe.WardrobeViewModel
import com.stathis.smartassistant.util.setScreenTitle


class WardrobeIntroFragment :
    BaseFragment<FragmentWardrobeIntroBinding>(R.layout.fragment_wardrobe_intro) {

    private val sharedViewModel: WardrobeViewModel by activityViewModels()
    private val viewModel: WardrobeIntroViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_intro_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.onCategoryClick {
            sharedViewModel.category = it.category
            goToShoesScreen()
        }
    }

    override fun stopOps() {}

/*
     * Navigates to the transport screen via safeargs
     */

    private fun goToShoesScreen() {
        val action = WardrobeIntroFragmentDirections.goToShoesScreen()
        findNavController().navigate(action)
    }
}