package com.stathis.smartassistant.ui.wardrobe.shoes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderShoesItemBinding

class ShoesAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, ShoesViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        val view = HolderShoesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}