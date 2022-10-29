package com.stathis.smartassistant.ui.rooms.fridge.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.models.rooms.FridgeDetail

class FridgeDetailsViewHolder(val binding: ViewDataBinding) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is FridgeDetail -> binding.setVariable(BR.model, data)
        }
    }
}