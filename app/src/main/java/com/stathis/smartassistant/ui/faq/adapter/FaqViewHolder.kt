package com.stathis.smartassistant.ui.faq.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.stathis.smartassistant.BR
import com.stathis.smartassistant.abstraction.BaseViewHolder
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.databinding.HolderFaqItemBinding
import com.stathis.smartassistant.models.Faq

class FaqViewHolder(val binding: ViewDataBinding) :
    BaseViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is Faq -> {
                binding.setVariable(BR.model, data)
                (binding as HolderFaqItemBinding).lessonCard.setOnClickListener {
                    data.isExpanded = !data.isExpanded

                    when (data.isExpanded) {
                        true -> {
                            binding.iconMoreImgView.animate().rotation(90f).start()
                            binding.lessonDesc.visibility = View.VISIBLE
                        }
                        false -> {
                            binding.iconMoreImgView.animate().rotation(0f).start()
                            binding.lessonDesc.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}