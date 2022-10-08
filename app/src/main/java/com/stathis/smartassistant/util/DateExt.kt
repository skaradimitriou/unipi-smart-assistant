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

