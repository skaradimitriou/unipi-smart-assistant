package com.stathis.smartassistant.ui.wardrobe.shoes

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.callbacks.wardrobe.ShoesCallback
import com.stathis.smartassistant.databinding.FragmentShoesBinding
import com.stathis.smartassistant.models.wardrobe.AddShoePromo
import com.stathis.smartassistant.models.wardrobe.Shoes
import com.stathis.smartassistant.ui.wardrobe.WardrobeViewModel
import com.stathis.smartassistant.util.setScreenTitle

class ShoesFragment : BaseFragment<FragmentShoesBinding>(R.layout.fragment_shoes) {

    private val viewModel: ShoesViewModel by viewModels()
    private val sharedViewModel: WardrobeViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_choose_shoes))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner, object : ShoesCallback {
            override fun onShoesTap(shoes: Shoes) = goToNewShoesScreen()
            override fun onAddItemTap(promo: AddShoePromo) = goToEshopScreen()
        })

        sharedViewModel.category?.let { category ->
            viewModel.getShoes(category.name)
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Navigates to the eshop screen via safeargs
     */

    private fun goToNewShoesScreen() {
        //FIXME: Add new shoes screen
    }

    /*
     * Navigates to the eshop screen via safeargs
     */

    private fun goToEshopScreen() {
        val action = ShoesFragmentDirections.goToEshopsScreen()
        findNavController().navigate(action)
    }
}