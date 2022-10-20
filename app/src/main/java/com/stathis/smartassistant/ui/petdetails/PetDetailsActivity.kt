package com.stathis.smartassistant.ui.petdetails

import android.view.MenuItem
import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityPetDetailsBinding

class PetDetailsActivity : BaseActivity<ActivityPetDetailsBinding>(R.layout.activity_pet_details) {

    private val viewModel: PetDetailsViewModel by viewModels()

    override fun init() {
        title = getString(R.string.pet_details_screen_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.bindList()
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }
}