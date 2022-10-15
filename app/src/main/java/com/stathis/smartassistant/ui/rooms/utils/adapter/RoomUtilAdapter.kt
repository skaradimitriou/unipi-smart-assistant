package com.stathis.smartassistant.ui.rooms.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderRoomUtilItemBinding

class RoomUtilAdapter(val callback: ItemCallback) :
    ListAdapter<LocalModel, RoomUtilViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomUtilViewHolder {
        val view = HolderRoomUtilItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomUtilViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RoomUtilViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}