package com.stathis.smartassistant.ui.dashboard.home.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo

class HomeViewHolder(val binding: ViewDataBinding, val callback: ItemCallback) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is RoomPromo -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
            is SmartLockerPromo -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}