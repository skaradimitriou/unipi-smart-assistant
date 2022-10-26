package com.stathis.smartassistant.ui.energy.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.models.EnergyModel

class EnergyViewHolder(val binding: ViewDataBinding) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is EnergyModel -> binding.setVariable(BR.model, data)
        }
    }
}