package com.stathis.smartassistant.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderHomeRoomItemBinding

class HomeScreenAdapter(val callback: ItemCallback) :
    ListAdapter<LocalModel, HomeScreenViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenViewHolder {
        val view =
            HolderHomeRoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}