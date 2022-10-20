package com.stathis.smartassistant.ui.petdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.databinding.HolderPetUtilItemBinding

class PetDetailsAdapter() :
    ListAdapter<LocalModel, PetDetailsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetDetailsViewHolder {
        val view = HolderPetUtilItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}