package com.stathis.smartassistant.models.wardrobe

import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.util.toUiText

data class WardrobeCategory(
    val imageResource: Int,
    val category: ShoeCategory
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is WardrobeCategory -> category == obj.category && imageResource == obj.imageResource
        else -> false
    }

    fun getCategoryName() : String = category.toUiText()
}