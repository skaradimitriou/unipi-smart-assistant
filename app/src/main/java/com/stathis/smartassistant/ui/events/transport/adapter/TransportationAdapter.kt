package com.stathis.smartassistant.ui.events.transport.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderTransportationOptionItemBinding

class TransportationAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, TransportationViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportationViewHolder {
       val view = HolderTransportationOptionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransportationViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: TransportationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}