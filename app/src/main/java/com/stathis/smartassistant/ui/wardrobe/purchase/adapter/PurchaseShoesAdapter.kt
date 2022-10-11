package com.stathis.smartassistant.ui.wardrobe.purchase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderPurchaseShoesItemBinding

class PurchaseShoesAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, PurchaseShoesViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseShoesViewHolder {
        val view = HolderPurchaseShoesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchaseShoesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PurchaseShoesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}