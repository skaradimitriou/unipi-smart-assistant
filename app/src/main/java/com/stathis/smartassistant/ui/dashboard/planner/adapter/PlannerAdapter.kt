package com.stathis.smartassistant.ui.dashboard.planner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderEventItemRowBinding

class PlannerAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, PlannerViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannerViewHolder {
        val view = HolderEventItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlannerViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PlannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}