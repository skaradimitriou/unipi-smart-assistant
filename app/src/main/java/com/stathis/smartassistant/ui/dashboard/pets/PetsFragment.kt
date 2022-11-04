package com.stathis.smartassistant.ui.dashboard.pets

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.callbacks.pets.PetLandingCallback
import com.stathis.smartassistant.databinding.FragmentPetsBinding
import com.stathis.smartassistant.models.pets.Pet
import com.stathis.smartassistant.models.pets.PetsPromo
import com.stathis.smartassistant.ui.feed.FeedingActivity
import com.stathis.smartassistant.ui.petdetails.PetDetailsActivity
import com.stathis.smartassistant.util.PET_DETAILS
import com.stathis.smartassistant.util.setScreenTitle

class PetsFragment : BaseFragment<FragmentPetsBinding>(R.layout.fragment_pets) {

    private val viewModel: PetsViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.nav_pets))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.getData()
        viewModel.observe(viewLifecycleOwner, object : PetLandingCallback {
            override fun onPetPromoTap(promo: PetsPromo) {
                goToFeedingFlow()
            }

            override fun onPetTap(pet: Pet) = goToPetDetails(pet)
        })
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }

    /*
     * Opens Pet Details Activity and passes the model as parcelable
     */
    private fun goToPetDetails(pet: Pet) {
        startActivity(Intent(requireContext(), PetDetailsActivity::class.java).apply {
            putExtra(PET_DETAILS, pet)
        })
    }

    /*
     * Starts the pet feeding flow
     */

    private fun goToFeedingFlow() {
        startActivity(Intent(requireContext(), FeedingActivity::class.java))
    }
}