package com.stathis.smartassistant.ui.dashboard.home.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo
import com.stathis.smartassistant.models.rooms.GeneralUtilsParent

class HomeViewHolder(val binding: ViewDataBinding, val callback: ItemCallback) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is GeneralUtilsParent -> {
                val adapter = HomeUtilsAdapter(callback)
                adapter.submitList(data.utils)
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.adapter, adapter)
            }
            is RoomPromo -> setData(data, callback)
            is SmartLockerPromo -> setData(data, callback)
        }
    }

    private fun setData(data: LocalModel, callback: ItemCallback) {
        binding.setVariable(BR.model, data)
        binding.setVariable(BR.callback, callback)
    }
}