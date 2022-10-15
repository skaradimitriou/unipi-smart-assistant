package com.stathis.smartassistant.ui.rooms.intro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderHomeRoomItemBinding

class RoomsAdapter(val callback: ItemCallback) :
    ListAdapter<LocalModel, RoomsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val view = HolderHomeRoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}