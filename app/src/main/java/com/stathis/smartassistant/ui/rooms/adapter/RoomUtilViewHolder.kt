package com.stathis.smartassistant.ui.rooms.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.RoomUtil
import com.stathis.smartassistant.BR

class RoomUtilViewHolder(val binding: ViewDataBinding, val callback: ItemCallback) : BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is RoomUtil -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}