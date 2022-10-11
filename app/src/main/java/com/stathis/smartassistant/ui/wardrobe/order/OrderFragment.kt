package com.stathis.smartassistant.ui.wardrobe.order

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentOrderBinding
import com.stathis.smartassistant.ui.wardrobe.WardrobeViewModel
import com.stathis.smartassistant.util.setScreenTitle


class OrderFragment : BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {

    private val viewModel: OrderViewModel by viewModels()
    private val sharedViewModel: WardrobeViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.order_screen_title))
    }

    override fun startOps() {
        binding.shoes = sharedViewModel.purchaseShoes
        binding.addressInfo = sharedViewModel.addressInfo
        binding.eshop = sharedViewModel.eshop

        binding.nextButton.setOnClickListener {
            goToOrderResultScreen()
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the order result screen via safeargs
     */

    private fun goToOrderResultScreen() {
        val action = OrderFragmentDirections.goToOrderResultScreen()
        findNavController().navigate(action)
    }
}