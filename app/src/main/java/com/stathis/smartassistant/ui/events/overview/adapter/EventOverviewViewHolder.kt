package com.stathis.smartassistant.ui.events.overview.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.models.Event

class EventOverviewViewHolder(val binding: ViewDataBinding) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is Event -> binding.setVariable(BR.model, data)
        }
    }
}