package com.stathis.smartassistant.util

import android.text.SpannableStringBuilder
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.stathis.smartassistant.R

/**
 * This file contains the extension functions that are used across the app
 */


/**
 * Helper fun to load an Image url into an imageview with glide.
 * The mechanism loads a local image as a fallback mechanism when url is null
 */

fun ImageView.loadImage(url: String? = null) {
    url?.let {
        Glide.with(this.context).load(url).into(this)
    } ?: kotlin.run {
        this.setImageResource(R.mipmap.ic_launcher)
    }
}

/**
 * Helper fun to simplify the static user greeting of the application
 */

fun TextView.setUserGreeting() {
    val greeting = SpannableStringBuilder()
        .append("Καλησπέρα")
        .append(" ")
        .bold { append("Γιάννη") }

    text = greeting
}