package com.stathis.smartassistant.models.rooms

import com.stathis.smartassistant.abstraction.LocalModel

data class GeneralUtil(
    val title: String,
    var enabled: Boolean
) : LocalModel {
    constructor() : this("", false)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is GeneralUtil -> title == obj.title && enabled == obj.enabled
        else -> false
    }
}
