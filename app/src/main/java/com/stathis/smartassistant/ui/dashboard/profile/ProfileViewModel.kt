package com.stathis.smartassistant.ui.dashboard.profile

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.ProfileOptionCallback
import com.stathis.smartassistant.models.ProfileOption
import com.stathis.smartassistant.models.UserData
import com.stathis.smartassistant.ui.dashboard.profile.adapter.ProfileOptionsAdapter
import kotlinx.coroutines.launch

class ProfileViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = ProfileOptionsAdapter(this)
    val userData = MutableLiveData<UserData>()
    private lateinit var callback: ProfileOptionCallback

    init {
        viewModelScope.launch {
            getUserData()
        }
    }

    fun registerCallback(callback: ProfileOptionCallback) {
        this.callback = callback

        val list = listOf(
            ProfileOption("Option #1"),
            ProfileOption("Option #2"),
            ProfileOption("Option #3"),
            ProfileOption("Option #4")
        )

        adapter.submitList(list)
    }

    private fun getUserData() {
        val userPhoto = "https://www.bnl.gov/today/body_pics/2017/06/stephanhruszkewycz-hr.jpg"
        //val username = getString(R.string.username)
        //val address = getString(R.string.user_address)

        //val user = UserData(username,userPhoto,address)
        //userData.value = user
    }

    override fun onItemTap(view: View) {
        when (view.tag) {
            is ProfileOption -> callback.onProfileOptionTap(view.tag as ProfileOption)
        }
    }
}