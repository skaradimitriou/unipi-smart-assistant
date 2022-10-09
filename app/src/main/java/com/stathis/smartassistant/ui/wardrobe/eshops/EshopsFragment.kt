package com.stathis.smartassistant.ui.wardrobe.eshops

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentEshopsBinding
import com.stathis.smartassistant.util.setScreenTitle

class EshopsFragment : BaseFragment<FragmentEshopsBinding>(R.layout.fragment_eshops) {

    private val viewModel: EshopsViewModel by viewModels()

    override fun init() {
        setScreenTitle("Επιλογή E-shop")
    }

    override fun startOps() {
    }

    override fun stopOps() {

    }
}