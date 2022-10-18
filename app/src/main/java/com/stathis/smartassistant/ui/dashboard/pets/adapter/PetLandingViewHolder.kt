package com.stathis.smartassistant.ui.dashboard.pets.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.pets.MyPetsPromo
import com.stathis.smartassistant.models.pets.PetsPromo

class PetLandingViewHolder(val binding: ViewDataBinding, val callback: ItemCallback) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is PetsPromo -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
            is MyPetsPromo -> {
                val adapter = PetLandingChildAdapter(callback)
                adapter.submitList(data.petList)
                binding.setVariable(BR.adapter, adapter)
                binding.setVariable(BR.model, data)
            }
        }
    }
}