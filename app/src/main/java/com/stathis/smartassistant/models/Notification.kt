package com.stathis.smartassistant.models

import com.google.firebase.Timestamp
import com.stathis.smartassistant.abstraction.LocalModel
import java.util.*

data class Notification(
    val title: String,
    val description: String,
    val date: Date,
    val timestamp: Timestamp = Timestamp(date),
    val hasBeenRead: Boolean
) : LocalModel {
    constructor() : this("", "", Date(), Timestamp(Date()), false)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Notification -> title == obj.title && description == obj.description && hasBeenRead == obj.hasBeenRead
        else -> false
    }
}
