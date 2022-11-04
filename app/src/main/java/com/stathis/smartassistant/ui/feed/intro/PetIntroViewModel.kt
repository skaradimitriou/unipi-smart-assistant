package com.stathis.smartassistant.ui.feed.intro

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.feeding.PetCallback
import com.stathis.smartassistant.models.pets.Pet
import com.stathis.smartassistant.models.pets.PetCategory
import com.stathis.smartassistant.models.pets.PetColor
import com.stathis.smartassistant.ui.dashboard.pets.adapter.PetLandingChildAdapter

class PetIntroViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    private lateinit var callback: PetCallback

    val adapter = PetLandingChildAdapter(this)

    val pets: LiveData<List<Pet>>
        get() = _pets

    private val _pets: MutableLiveData<List<Pet>> = MutableLiveData(listOf())

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

        _pets.value = myPets
    }

    fun observe(owner: LifecycleOwner, callback: PetCallback) {
        this.callback = callback
        pets.observe(owner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onItemTap(view: View) {
        when (view.tag) {
            is Pet -> callback.onPetTap(view.tag as Pet)
            else -> Unit
        }
    }
}