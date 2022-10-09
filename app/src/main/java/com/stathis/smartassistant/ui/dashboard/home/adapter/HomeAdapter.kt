package com.stathis.smartassistant.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderEmptyItemBinding
import com.stathis.smartassistant.databinding.HolderHomeRoomPromoItemBinding
import com.stathis.smartassistant.databinding.HolderHomeSmartLockerItemBinding
import com.stathis.smartassistant.models.HomeUtil
import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo

class HomeAdapter(val callback: ItemCallback) : ListAdapter<LocalModel, HomeViewHolder>(
    DiffUtilClass<LocalModel>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_home_room_promo_item -> HolderHomeRoomPromoItemBinding.inflate(inflater, parent, false)
            R.layout.holder_home_smart_locker_item -> HolderHomeSmartLockerItemBinding.inflate(inflater, parent,false)
            else -> HolderEmptyItemBinding.inflate(inflater, parent, false)
        }
        return HomeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is HomeUtil -> R.layout.holder_empty_item
        is RoomPromo -> R.layout.holder_home_room_promo_item
        is SmartLockerPromo -> R.layout.holder_home_smart_locker_item
        else -> R.layout.holder_empty_item
    }
}