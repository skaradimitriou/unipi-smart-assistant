package com.stathis.smartassistant.ui.events.coffee

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentCoffeeOrderBinding

class CoffeeOrderFragment : BaseFragment<FragmentCoffeeOrderBinding>(R.layout.fragment_coffee_order) {

    val viewModel : CoffeeOrderViewModel by viewModels()

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