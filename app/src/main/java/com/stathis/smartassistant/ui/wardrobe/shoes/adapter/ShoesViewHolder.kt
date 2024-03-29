package com.stathis.smartassistant.ui.wardrobe.shoes.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.wardrobe.AddShoePromo
import com.stathis.smartassistant.models.wardrobe.Shoes

class ShoesViewHolder(val binding: ViewDataBinding, val callback: ItemCallback) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is Shoes -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
            is AddShoePromo -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}