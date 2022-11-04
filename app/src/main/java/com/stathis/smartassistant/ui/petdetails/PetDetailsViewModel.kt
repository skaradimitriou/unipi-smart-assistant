package com.stathis.smartassistant.ui.petdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.pets.PetUtil
import com.stathis.smartassistant.ui.petdetails.adapter.PetDetailsAdapter
import kotlin.random.Random

class PetDetailsViewModel(val app: Application) : BaseViewModel(app) {

    val adapter = PetDetailsAdapter()

    fun bindList() {
        val list = listOf(
            PetUtil(
                title = getString(R.string.need_for_food),
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = getString(R.string.need_for_water),
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = getString(R.string.need_to_play),
                progress = Random.nextInt(20, 100)
            )
        )

        adapter.submitList(list)
    }
}