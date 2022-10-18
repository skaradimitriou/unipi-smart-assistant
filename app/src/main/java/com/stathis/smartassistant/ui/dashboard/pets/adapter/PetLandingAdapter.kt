package com.stathis.smartassistant.ui.dashboard.pets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.DiffUtilClass
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.databinding.HolderEmptyItemBinding
import com.stathis.smartassistant.databinding.HolderMyPetsPromoBinding
import com.stathis.smartassistant.databinding.HolderPetPromoItemBinding
import com.stathis.smartassistant.models.pets.MyPetsPromo
import com.stathis.smartassistant.models.pets.PetsPromo

class PetLandingAdapter(private val callback: ItemCallback) :
    ListAdapter<LocalModel, PetLandingViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetLandingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_pet_promo_item -> HolderPetPromoItemBinding.inflate(inflater, parent, false)
            R.layout.holder_my_pets_promo -> HolderMyPetsPromoBinding.inflate(inflater, parent, false)
            else -> HolderEmptyItemBinding.inflate(inflater, parent, false)
        }
        return PetLandingViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PetLandingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is PetsPromo -> R.layout.holder_pet_promo_item
        is MyPetsPromo -> R.layout.holder_my_pets_promo
        else -> R.layout.holder_empty_item
    }
}