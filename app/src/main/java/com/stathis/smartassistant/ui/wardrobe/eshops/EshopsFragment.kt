package com.stathis.smartassistant.ui.wardrobe.eshops

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentEshopsBinding
import com.stathis.smartassistant.ui.wardrobe.WardrobeViewModel
import com.stathis.smartassistant.ui.wardrobe.purchase.PurchaseShoesFragmentDirections
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar

class EshopsFragment : BaseFragment<FragmentEshopsBinding>(R.layout.fragment_eshops) {

    private val viewModel: EshopsViewModel by viewModels()
    private val sharedViewModel : WardrobeViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_eshops_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner) { selectedEshop ->
            sharedViewModel.eshop = selectedEshop
            goToOrderScreen()
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Navigates to the order screen via safeargs
     */

    private fun goToOrderScreen() {
        val action = EshopsFragmentDirections.goToOrderScreen()
        findNavController().navigate(action)
    }
}