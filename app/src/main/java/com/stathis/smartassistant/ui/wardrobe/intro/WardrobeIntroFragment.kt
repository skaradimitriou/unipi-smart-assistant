package com.stathis.smartassistant.ui.wardrobe.intro

import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentWardrobeIntroBinding
import com.stathis.smartassistant.util.setScreenTitle


class WardrobeIntroFragment : BaseFragment<FragmentWardrobeIntroBinding>(R.layout.fragment_wardrobe_intro) {

    override fun init() {
        setScreenTitle("Επιλογή Κατηγορίας")
    }

    override fun startOps() {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.shoesFragment)
        }
    }

    override fun stopOps() {

    }
}