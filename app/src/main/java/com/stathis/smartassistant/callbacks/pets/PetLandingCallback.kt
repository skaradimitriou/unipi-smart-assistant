package com.stathis.smartassistant.callbacks.pets

import com.stathis.smartassistant.models.pets.Pet
import com.stathis.smartassistant.models.pets.PetsPromo

interface PetLandingCallback {
    fun onPetPromoTap(promo: PetsPromo)
    fun onPetTap(pet: Pet)
}