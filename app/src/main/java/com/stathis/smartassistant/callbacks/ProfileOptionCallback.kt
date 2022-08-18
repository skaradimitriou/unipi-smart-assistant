package com.stathis.smartassistant.callbacks

import com.stathis.smartassistant.models.ProfileOption

fun interface ProfileOptionCallback {
    fun onProfileOptionTap(option : ProfileOption)
}