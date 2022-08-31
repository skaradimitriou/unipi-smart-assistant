package com.stathis.smartassistant.util

import com.stathis.smartassistant.models.TransportationOption

/**
 * This file maps content into not null types for the ui
 */

fun String?.toUiModel() = this ?: ""
fun Int?.toUiModel() = this ?: 0


fun TransportationOption?.toUiModel(): TransportationOption {
    return this ?: TransportationOption(
        this?.title.toUiModel(),
        this?.image.toUiModel(),
    )
}