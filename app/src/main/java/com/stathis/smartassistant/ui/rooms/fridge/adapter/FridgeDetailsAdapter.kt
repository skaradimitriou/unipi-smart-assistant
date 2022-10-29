package com.stathis.smartassistant.ui.rooms.fridge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.databinding.HolderFridgeItemBinding

class FridgeDetailsAdapter() :
    ListAdapter<LocalModel, FridgeDetailsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeDetailsViewHolder {
        val view =
            HolderFridgeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FridgeDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FridgeDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}