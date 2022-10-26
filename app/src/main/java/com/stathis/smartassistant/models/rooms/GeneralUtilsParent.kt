package com.stathis.smartassistant.models.rooms

import com.stathis.smartassistant.abstraction.LocalModel

data class GeneralUtilsParent(
    val title: String,
    var utils: List<GeneralUtil>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is GeneralUtilsParent -> title == obj.title && utils == obj.utils
        else -> false
    }
}
