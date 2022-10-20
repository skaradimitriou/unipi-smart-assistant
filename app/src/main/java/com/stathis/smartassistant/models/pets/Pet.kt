package com.stathis.smartassistant.models.pets

import com.stathis.smartassistant.abstraction.LocalModel

data class Pet(
    val nickname: String,
    val image: Int,
    val type: PetCategory,
    val color: PetColor,
    val utils: List<PetUtil>? = null
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Pet -> nickname == obj.nickname && type == obj.type && color == obj.color
        else -> false
    }
}

enum class PetCategory {
    CAT, DOG
}

enum class PetColor {
    BLACK, GREY, WHITE
}

data class MyPetsPromo(
    val title: String,
    val petList: List<Pet>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is MyPetsPromo -> title == obj.title && petList == obj.petList
        else -> false
    }
}

data class PetUtil(
    val title: String,
    val progress: Int
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is PetUtil -> title == obj.title && progress == obj.progress
        else -> false
    }
}