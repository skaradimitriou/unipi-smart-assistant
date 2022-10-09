package com.stathis.smartassistant.ui.wardrobe.intro

import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentWardrobeIntroBinding
import com.stathis.smartassistant.util.setScreenTitle


class WardrobeIntroFragment : BaseFragment<FragmentWardrobeIntroBinding>(R.layout.fragment_wardrobe_intro) {

    override fun init() {
        setScreenTitle("Επιλογή Κατηγορίας")
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}