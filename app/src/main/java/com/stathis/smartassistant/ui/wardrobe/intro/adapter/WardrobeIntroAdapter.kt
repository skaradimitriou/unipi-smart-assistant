package com.stathis.smartassistant.ui.wardrobe.intro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderWardrobeCategoryItemBinding

class WardrobeIntroAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, WardrobeIntroViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WardrobeIntroViewHolder {
        val view = HolderWardrobeCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WardrobeIntroViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: WardrobeIntroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}