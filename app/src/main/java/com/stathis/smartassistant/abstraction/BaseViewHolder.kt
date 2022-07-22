package com.stathis.smartassistant.abstraction

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data : LocalModel){
        present(data)
    }

    abstract fun present(data : LocalModel)
}