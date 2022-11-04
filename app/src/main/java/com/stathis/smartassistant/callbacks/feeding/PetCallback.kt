package com.stathis.smartassistant.callbacks.feeding

import com.stathis.smartassistant.models.pets.Pet

fun interface PetCallback {
    fun onPetTap(model: Pet)
}