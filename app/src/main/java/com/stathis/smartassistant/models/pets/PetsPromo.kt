package com.stathis.smartassistant.models.pets

import com.stathis.smartassistant.abstraction.LocalModel

data class PetsPromo(
    val title: String,
    val description: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is PetsPromo -> title == obj.title && description == obj.description
        else -> false
    }
}