package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class Room(
    val title: String,
    val image : Int
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj) {
        is Room -> title == obj.title
        else -> false
    }
}
