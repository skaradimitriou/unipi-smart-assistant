package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class OnboardingItem(
    val imageResource: Int,
    val title: String,
    val description: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is OnboardingItem -> title == obj.title && description == obj.description
        else -> false
    }
}
