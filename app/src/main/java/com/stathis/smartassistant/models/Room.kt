package com.stathis.smartassistant.models

import android.os.Parcelable
import com.stathis.smartassistant.abstraction.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val title: String,
    val image: String,
    val sequence: Int,
    var utils: List<RoomUtil>
) : LocalModel, Parcelable {
    constructor() : this("", "", 0, listOf())

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Room -> title == obj.title
        else -> false
    }
}

@Parcelize
data class RoomUtil(
    val title: String,
    var enabled: Boolean,
    var heatLevel: Int? = null
) : Parcelable, LocalModel {
    constructor() : this("", false, 0)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is RoomUtil -> title == obj.title
        else -> false
    }
}