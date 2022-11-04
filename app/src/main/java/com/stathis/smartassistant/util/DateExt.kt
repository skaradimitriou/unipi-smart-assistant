package com.stathis.smartassistant.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper functions for Dates
 */

@SuppressLint("SimpleDateFormat")
fun String.getDay(): String {
    val inputFormat = SimpleDateFormat("dd/MM/yyyy")
    val outputFormat = SimpleDateFormat("dd")
    val test = inputFormat.parse(this) ?: Date()
    return outputFormat.format(test)
}

@SuppressLint("SimpleDateFormat")
fun String.getMonthName(): String {
    val inputFormat = SimpleDateFormat("dd/MM/yyyy")
    val outputFormat = SimpleDateFormat("MMM")
    val test = inputFormat.parse(this) ?: Date()
    return outputFormat.format(test)
}

/**
 * Helper fun to get the full date in the format of: Κυριακή, 9 Οκτωβρίου 2022
 */

@SuppressLint("SimpleDateFormat")
fun getCurrentFullDate(): String {
    val date = Date()
    val outputFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("el", "GR"))
    return outputFormat.format(date)
}

/**
 * Helper fun to get the current time in the format of: 22:50
 */

fun getCurrentTime(): String {
    val date = Date()
    val outputFormat = SimpleDateFormat("HH", Locale("el", "GR"))
    return outputFormat.format(date)
}

/**
 * Helper fun to get the full date in the format of: Κυριακή, 9 Οκτωβρίου 2022
 */

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val date = Date()
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale("el", "GR"))
    return outputFormat.format(date)
}