package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel
import kotlin.random.Random

data class TransportationOption(
    val title: String,
    val image: Int,
    val estimatedMinutes: Int = Random.nextInt(15, 59),
    var isTheFasestWay: Boolean? = false
) : LocalModel {
    constructor() : this("", 0, 0, false)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is TransportationOption -> title == obj.title && image == obj.image
        else -> false
    }
}
