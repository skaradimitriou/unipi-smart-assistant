package com.stathis.smartassistant.ui.energy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.databinding.HolderEnergyMonthItemBinding

class EnergyAdapter() : ListAdapter<LocalModel, EnergyViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnergyViewHolder {
        val view = HolderEnergyMonthItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EnergyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EnergyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}