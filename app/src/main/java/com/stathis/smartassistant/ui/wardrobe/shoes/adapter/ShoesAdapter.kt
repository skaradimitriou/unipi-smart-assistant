package com.stathis.smartassistant.ui.wardrobe.shoes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderAddShoesPromoItemBinding
import com.stathis.smartassistant.databinding.HolderShoesItemBinding
import com.stathis.smartassistant.models.wardrobe.Shoes

class ShoesAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, ShoesViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_shoes_item -> HolderShoesItemBinding.inflate(inflater, parent, false)
            else -> HolderAddShoesPromoItemBinding.inflate(inflater, parent, false)
        }
        return ShoesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Shoes -> R.layout.holder_shoes_item
        else -> R.layout.holder_add_shoes_promo_item
    }
}