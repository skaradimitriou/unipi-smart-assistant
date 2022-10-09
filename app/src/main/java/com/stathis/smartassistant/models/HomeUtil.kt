package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class HomeUtil(
    val title: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
