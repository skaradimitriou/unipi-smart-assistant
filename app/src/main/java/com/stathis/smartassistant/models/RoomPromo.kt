package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class RoomPromo(
    val title: String,
    val description: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}