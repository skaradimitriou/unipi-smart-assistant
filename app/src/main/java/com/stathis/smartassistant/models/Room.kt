package com.stathis.smartassistant.models

import android.os.Parcelable
import com.stathis.smartassistant.abstraction.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val title: String,
    val image: String,
    val sequence: Int,
    val utils: List<RoomUtil>
) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Room -> title == obj.title
        else -> false
    }
}

@Parcelize
data class RoomUtil(
    val title: String,
    val enabled: Boolean
) : Parcelable, LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is RoomUtil -> title == obj.title
        else -> false
    }
}