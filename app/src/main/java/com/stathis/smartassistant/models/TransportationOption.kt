package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class TransportationOption(
    val title: String,
    val image: Int
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is TransportationOption -> title == obj.title && image == obj.image
        else -> false
    }
}
