package com.stathis.smartassistant.models

import com.stathis.smartassistant.abstraction.LocalModel

data class UserData(
    val username: String,
    val userImg: String,
    val address: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is UserData -> username == obj.username && userImg == obj.userImg && address == obj.address
        else -> false
    }
}
