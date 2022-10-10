package com.stathis.smartassistant.callbacks.wardrobe

import com.stathis.smartassistant.models.wardrobe.WardrobeCategory

fun interface CategoryCallback {
    fun onCategoryTap(category: WardrobeCategory)
}