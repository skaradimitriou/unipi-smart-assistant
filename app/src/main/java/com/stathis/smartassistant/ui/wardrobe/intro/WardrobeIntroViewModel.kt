package com.stathis.smartassistant.ui.wardrobe.intro

import android.view.View
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.R
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.CategoryCallback
import com.stathis.smartassistant.models.wardrobe.ShoeCategory
import com.stathis.smartassistant.models.wardrobe.WardrobeCategory
import com.stathis.smartassistant.ui.wardrobe.intro.adapter.WardrobeIntroAdapter

class WardrobeIntroViewModel : ViewModel(), ItemCallback {

    val adapter = WardrobeIntroAdapter(this)
    private lateinit var callback: CategoryCallback

    private val categories = listOf(
        WardrobeCategory(R.drawable.vans, ShoeCategory.EVERYDAY),
        WardrobeCategory(R.drawable.pegasus, ShoeCategory.SPORTS),
        WardrobeCategory(R.drawable.oxfords, ShoeCategory.SPECIAL)
    )

    fun onCategoryClick(callback: CategoryCallback) {
        this.callback = callback
        adapter.submitList(categories)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is WardrobeCategory -> callback.onCategoryTap(view.tag as WardrobeCategory)
        else -> Unit
    }
}