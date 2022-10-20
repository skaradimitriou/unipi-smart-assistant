package com.stathis.smartassistant.ui.petdetails

import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.models.pets.PetUtil
import com.stathis.smartassistant.ui.petdetails.adapter.PetDetailsAdapter
import kotlin.random.Random

class PetDetailsViewModel : ViewModel() {

    val adapter = PetDetailsAdapter()

    fun bindList() {
        val list = listOf(
            PetUtil(
                title = "Title #1",
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = "Title #2",
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = "Title #3",
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = "Title #4",
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = "Title #5",
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = "Title #6",
                progress = Random.nextInt(20, 100)
            ),
            PetUtil(
                title = "Title #7",
                progress = Random.nextInt(20, 100)
            )
        )

        adapter.submitList(list)
    }
}