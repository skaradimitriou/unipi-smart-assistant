package com.stathis.smartassistant.util

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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


fun TextInputEditText.afterTextChanged(input: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            input.invoke(editable.toString())
        }
    })

}fun TextInputEditText.onTextChanged(input: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = input.invoke(p0.toString())
        override fun afterTextChanged(editable: Editable?) {}
    })
}