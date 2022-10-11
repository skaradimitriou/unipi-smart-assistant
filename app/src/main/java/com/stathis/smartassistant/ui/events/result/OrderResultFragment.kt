package com.stathis.smartassistant.ui.events.result

import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentOrderResultBinding
import com.stathis.smartassistant.util.setScreenTitle

class OrderResultFragment : BaseFragment<FragmentOrderResultBinding>(R.layout.fragment_order_result) {

    override fun init() {
        setScreenTitle(getString(R.string.order_result_screen_title))
    }

    override fun startOps() {
        binding.primaryButton.setOnClickListener {
            //returns to home screen in dashboard activity
            requireActivity().finish()
        }
    }

    override fun stopOps() {}
}