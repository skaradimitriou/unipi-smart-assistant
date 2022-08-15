package com.stathis.smartassistant.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.smartassistant.R

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