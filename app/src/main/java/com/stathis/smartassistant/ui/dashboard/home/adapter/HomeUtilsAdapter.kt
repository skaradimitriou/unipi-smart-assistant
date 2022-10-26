package com.stathis.smartassistant.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderHomeUtilItemBinding

class HomeUtilsAdapter(val callback: ItemCallback) : ListAdapter<LocalModel, HomeUtilsViewHolder>(
    DiffUtilClass<LocalModel>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeUtilsViewHolder {
        val view = HolderHomeUtilItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeUtilsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HomeUtilsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}