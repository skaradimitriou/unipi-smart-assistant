package com.stathis.smartassistant.ui.dashboard.pets

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.pets.PetLandingCallback
import com.stathis.smartassistant.models.pets.*
import com.stathis.smartassistant.ui.dashboard.pets.adapter.PetLandingAdapter

class PetsViewModel(app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = PetLandingAdapter(this)
    private val petLandingData = MutableLiveData<List<LocalModel>>()
    private lateinit var callback: PetLandingCallback

    fun getData() {
        val myPets = listOf(
            Pet(
                nickname = getString(R.string.makis),
                image = R.drawable.makis,
                type = PetCategory.CAT,
                color = PetColor.GREY,
            ),
            Pet(
                nickname = getString(R.string.sakis),
                image = R.drawable.sakis,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            ),
            Pet(
                nickname = getString(R.string.takis),
                image = R.drawable.takis,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            ),
            Pet(
                nickname = getString(R.string.melenia),
                image = R.drawable.melenia,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            ),
            Pet(
                nickname = getString(R.string.tenia),
                image = R.drawable.tenia,
                type = PetCategory.CAT,
                color = PetColor.BLACK
            )
        )

        val list = listOf(
            PetsPromo(
                title = getString(R.string.smart_feeding),
                description = getString(R.string.smart_feeding_subtitle),
                image = R.drawable.feeding
            ),
            MyPetsPromo(
                title = getString(R.string.my_pets_title),
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
