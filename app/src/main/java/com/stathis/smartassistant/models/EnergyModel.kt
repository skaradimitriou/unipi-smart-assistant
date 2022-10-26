package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class EnergyModel(
    val month: String,
    val seq: Int,
    val value: Int
) : LocalModel {
    constructor() : this("", 0, 0)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is EnergyModel -> seq == obj.seq && value == obj.value
        else -> false
    }
}
