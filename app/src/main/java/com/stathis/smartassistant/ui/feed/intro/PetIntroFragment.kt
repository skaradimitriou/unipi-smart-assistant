package com.stathis.smartassistant.ui.feed.intro

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPetIntroBinding
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle


class PetIntroFragment : BaseFragment<FragmentPetIntroBinding>(R.layout.fragment_pet_intro) {

    private val viewModel: PetIntroViewModel by viewModels()
    private val sharedViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.select_pet_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.getData()
        viewModel.observe(viewLifecycleOwner) { selectedPet ->
            sharedViewModel.pet = selectedPet
            goToFeedingOptionsScreen()
        }
    }

    /*
     * Navigates to feeding screen via safeargs
     */

    private fun goToFeedingOptionsScreen() {
        val action = PetIntroFragmentDirections.goToFeedingOptionsScreen()
        findNavController().navigate(action)
    }

    override fun stopOps() {}
}