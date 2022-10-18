package com.stathis.smartassistant.ui.petdetails

import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityPetDetailsBinding

class PetDetailsActivity : BaseActivity<ActivityPetDetailsBinding>(R.layout.activity_pet_details) {

    private val viewModel: PetDetailsViewModel by viewModels()

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