package com.stathis.smartassistant.ui.wardrobe.result

import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentWardrobeResultBinding
import com.stathis.smartassistant.util.setScreenTitle


class WardrobeResultFragment : BaseFragment<FragmentWardrobeResultBinding>(R.layout.fragment_wardrobe_result) {

    override fun init() {
        setScreenTitle(getString(R.string.success))
    }

    override fun startOps() {
        binding.returnToPlannerBtn.setOnClickListener {
            //returns to home screen in dashboard activity
            requireActivity().finish()
        }
    }

    override fun stopOps() {}
}