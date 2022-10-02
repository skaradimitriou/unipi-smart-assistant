package com.stathis.smartassistant.ui.events.coffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderCoffeeItemBinding

class CoffeeOrderAdapter(val callback: ItemCallback) :
    ListAdapter<LocalModel, CoffeeOrderViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeOrderViewHolder {
        val view = HolderCoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeOrderViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CoffeeOrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}