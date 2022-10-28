package com.stathis.smartassistant.ui.dashboard.notifications.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.models.Notification

class NotificationsViewHolder(val binding: ViewDataBinding) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is Notification -> binding.setVariable(BR.model, data)
        }
    }
}