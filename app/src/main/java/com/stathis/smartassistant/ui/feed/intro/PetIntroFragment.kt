package com.stathis.smartassistant.ui.feed.intro

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPetIntroBinding


class PetIntroFragment : BaseFragment<FragmentPetIntroBinding>(R.layout.fragment_pet_intro) {

    private val viewModel: PetIntroViewModel by viewModels()

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