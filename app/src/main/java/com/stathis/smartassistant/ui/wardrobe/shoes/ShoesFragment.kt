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
import com.stathis.smartassistant.util.showYesNoDialog

class ShoesFragment : BaseFragment<FragmentShoesBinding>(R.layout.fragment_shoes) {

    private val viewModel: ShoesViewModel by viewModels()
    private val sharedViewModel: WardrobeViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.wardrobe_choose_shoes))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(viewLifecycleOwner, object : ShoesCallback {
            override fun onShoesTap(shoes: Shoes) {
                sharedViewModel.event?.let {
                    showYesNoDialog(
                        message = getString(R.string.add_shoes_to_event),
                        onButtonClick = {
                            viewModel.updateShoesOnEvent(it, shoes)
                        })
                }
            }

            override fun onAddItemTap(promo: AddShoePromo) {
                val category = sharedViewModel.category?.name ?: ""
                goToPurchaseShoesScreen(category)
            }
        })

        sharedViewModel.category?.let { category ->
            viewModel.getShoes(category.name)
        }

        viewModel.shoesAddedToEvent.observe(viewLifecycleOwner) { addedSuccessfully ->
            if (addedSuccessfully) {
                goToResultScreen()
            } else {
                //FIXME: Display error on screen
            }
        }
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Navigates to the result screen via safeargs
     */

    private fun goToResultScreen() {
        val action = ShoesFragmentDirections.goToResultScreen()
        findNavController().navigate(action)
    }

    /*
     * Navigates to the purchase shoes screen via safeargs
     */

    private fun goToPurchaseShoesScreen(categoryType: String) {
        val action = ShoesFragmentDirections.goToPurchaseShoesScreen(categoryType)
        findNavController().navigate(action)
    }
}