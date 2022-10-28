package com.stathis.smartassistant.models

import com.google.firebase.Timestamp
import com.stathis.smartassistant.abstraction.LocalModel
import java.util.*

data class Notification(
    val title: String,
    val description: String,
    val category: NotificationType,
    val date: Date,
    val timestamp: Timestamp = Timestamp(date),
    var hasBeenRead: Boolean
) : LocalModel {
    constructor() : this("", "", NotificationType.DEFAULT, Date(), Timestamp(Date()), false)

    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is Notification -> title == obj.title && description == obj.description && hasBeenRead == obj.hasBeenRead
        else -> false
    }
}

enum class NotificationType {
    DEFAULT, EVENT, WARDROBE, PETS
}
