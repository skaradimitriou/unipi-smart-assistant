package com.stathis.smartassistant.ui.dashboard.pets

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.callbacks.pets.PetLandingCallback
import com.stathis.smartassistant.databinding.FragmentPetsBinding
import com.stathis.smartassistant.models.pets.Pet
import com.stathis.smartassistant.models.pets.PetsPromo
import com.stathis.smartassistant.ui.petdetails.PetDetailsActivity
import com.stathis.smartassistant.util.setScreenTitle
import timber.log.Timber

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
                Timber.d("CLICKED")
            }

            override fun onPetTap(pet: Pet) {
                startActivity(Intent(requireContext(), PetDetailsActivity::class.java))
            }
        })
    }

    override fun stopOps() {
        viewModel.release(viewLifecycleOwner)
    }
}