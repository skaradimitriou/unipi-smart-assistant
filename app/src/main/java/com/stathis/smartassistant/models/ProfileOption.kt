package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class ProfileOption(
    val title: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is ProfileOption -> title == obj.title
        else -> false
    }
}
