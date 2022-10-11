package com.stathis.smartassistant.ui.wardrobe.purchase

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPurchaseShoesBinding
import com.stathis.smartassistant.ui.wardrobe.WardrobeViewModel
import com.stathis.smartassistant.util.setScreenTitle


class PurchaseShoesFragment :
    BaseFragment<FragmentPurchaseShoesBinding>(R.layout.fragment_purchase_shoes) {

    private val viewModel: PurchaseShoesViewModel by viewModels()
    private val sharedViewModel: WardrobeViewModel by activityViewModels()
    private val args : PurchaseShoesFragmentArgs by navArgs()


    override fun init() {
        binding.viewModel = viewModel
        setScreenTitle(getString(R.string.purchase_shoes_title))
    }

    override fun startOps() {
        viewModel.getShoes(category = args.categoryType)

        viewModel.observe(viewLifecycleOwner) { selectedShoes ->
            sharedViewModel.purchaseShoes = selectedShoes
            goToPurchaseShoesScreen()
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Navigates to the eshop screen via safeargs
     */

    private fun goToPurchaseShoesScreen() {
        val action = PurchaseShoesFragmentDirections.goToEshopsScreen()
        findNavController().navigate(action)
    }
}