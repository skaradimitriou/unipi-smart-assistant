package com.stathis.smartassistant.ui.dashboard.pets

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.pets.PetLandingCallback
import com.stathis.smartassistant.models.pets.*
import com.stathis.smartassistant.ui.dashboard.pets.adapter.PetLandingAdapter

class PetsViewModel : ViewModel(), ItemCallback {

    val adapter = PetLandingAdapter(this)
    val petLandingData = MutableLiveData<List<LocalModel>>()
    private lateinit var callback: PetLandingCallback

    fun getData() {
        val myPets = listOf(
            Pet(
                nickname = "Makis",
                image = R.drawable.makis,
                type = PetCategory.CAT,
                color = PetColor.GREY,
            ),
            Pet(
                nickname = "Sakis",
                image = R.drawable.sakis,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            ),
            Pet(
                nickname = "Takis",
                image = R.drawable.takis,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            ),
            Pet(
                nickname = "Melenia",
                image = R.drawable.melenia,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            ),
            Pet(
                nickname = "Tenia",
                image = R.drawable.tenia,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            )
        )

        val list = listOf(
            PetsPromo(
                title = "This is a title",
                description = "This is the description of my pets promo"
            ),
            PetsPromo(
                title = "This is a title",
                description = "This is the description of my pets promo"
            ),
            MyPetsPromo(
                title = "Τα κατοικίδιά μου",
                petList = myPets
            )
        )

        petLandingData.value = list
    }

    fun observe(owner: LifecycleOwner, callback: PetLandingCallback) {
        this.callback = callback

        petLandingData.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        petLandingData.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is PetsPromo -> callback.onPetPromoTap(view.tag as PetsPromo)
        is Pet -> callback.onPetTap(view.tag as Pet)
        else -> Unit
    }
}
