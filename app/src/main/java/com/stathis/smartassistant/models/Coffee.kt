package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel
import kotlin.random.Random

data class Coffee(
    val title: String,
    val image: Int,
    var sugar: SugarType = SugarType.SWEET,
    val price: Double = Random.nextDouble(1.00, 2.20)
) : LocalModel {
    constructor() : this ("",0,SugarType.SWEET, 0.0)
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Coffee -> true
        else -> false
    }
}

enum class SugarType {
    SWEET, REGULAR, NO_SUGAR
}