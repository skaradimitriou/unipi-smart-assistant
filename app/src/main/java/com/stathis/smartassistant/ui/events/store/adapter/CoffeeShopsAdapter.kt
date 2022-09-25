package com.stathis.smartassistant.ui.events.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderCoffeeShopItemBinding

class CoffeeShopsAdapter(val callback: ItemCallback) :
    ListAdapter<LocalModel, CoffeeShopsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeShopsViewHolder {
        val view =
            HolderCoffeeShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeShopsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CoffeeShopsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}