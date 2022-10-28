package com.stathis.smartassistant.ui.dashboard.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.databinding.HolderNotificationsItemBinding

class NotificationsAdapter() :
    ListAdapter<LocalModel, NotificationsViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val view = HolderNotificationsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotificationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}