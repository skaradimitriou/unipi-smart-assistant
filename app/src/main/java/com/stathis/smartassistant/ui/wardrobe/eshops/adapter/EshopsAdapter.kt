package com.stathis.smartassistant.ui.wardrobe.eshops.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderEshopItemBinding

class EshopsAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, EshopsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EshopsViewHolder {
        val view = HolderEshopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EshopsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: EshopsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}