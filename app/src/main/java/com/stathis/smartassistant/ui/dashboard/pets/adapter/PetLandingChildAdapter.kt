package com.stathis.smartassistant.ui.dashboard.pets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderPetItemBinding

class PetLandingChildAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, PetLandingChildViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetLandingChildViewHolder {
        val view = HolderPetItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PetLandingChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PetLandingChildViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}