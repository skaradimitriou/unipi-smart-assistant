package com.stathis.smartassistant.ui.wardrobe.purchase.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.wardrobe.ShoesToBuy

class PurchaseShoesViewHolder(val binding: ViewDataBinding, val callback: ItemCallback) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is ShoesToBuy -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}