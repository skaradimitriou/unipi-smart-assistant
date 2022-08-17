package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class RoomUtil(
    val title: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is RoomUtil -> title == obj.title
        else -> false
    }
}
