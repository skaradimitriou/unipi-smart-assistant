package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class Faq(
    val title: String,
    val description: String,
    var isExpanded: Boolean = false
) : LocalModel {
    constructor() : this("", "", false)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Faq -> title == obj.title && description == obj.description
        else -> false
    }
}

