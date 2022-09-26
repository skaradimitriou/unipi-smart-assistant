package com.stathis.smartassistant.ui.events.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.databinding.HolderEventOverviewItemBinding

class EventOverviewAdapter() : ListAdapter<LocalModel, EventOverviewViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventOverviewViewHolder {
        val view = HolderEventOverviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventOverviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventOverviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}