package com.stathis.smartassistant.util

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.utils.widget.MotionLabel
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.smartassistant.R
import com.stathis.smartassistant.models.Event
import kotlinx.coroutines.NonCancellable.start

/**
 * This file contains the binding adapters that are used across the app
 */

@BindingAdapter("imageResource")
fun ImageView.imageResource(imageResource: Int? = null) {
    imageResource?.let {
        this.setImageResource(imageResource)
    } ?: kotlin.run {
        this.setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("loadImg")
fun ImageView.loadImg(url: String? = null) {
    url?.let {
        Glide.with(this.context).load(url).into(this)
    } ?: kotlin.run {
        setImageResource(R.mipmap.ic_launcher)
    }
}

@BindingAdapter("bindEventData")
fun TextView.bindEventData(event: Event) {
    text = this.context.getString(
        R.string.event_overview,
        event.title,
        event.transportationOption.title
    )
}