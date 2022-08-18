package com.stathis.smartassistant.ui.dashboard.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderProfileOptionItemBinding

class ProfileOptionsAdapter(val callback: ItemCallback) : ListAdapter<LocalModel, ProfileOptionsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionsViewHolder {
        val view = HolderProfileOptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileOptionsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProfileOptionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}