package com.stathis.smartassistant.ui.dashboard.home

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.HomeRoomCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.ui.dashboard.home.adapter.HomeScreenAdapter
import kotlinx.coroutines.launch

class HomeViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = HomeScreenAdapter(this)
    val userImg = MutableLiveData<String>()

    private lateinit var callback: HomeRoomCallback

    init {
        viewModelScope.launch {
            getUserImage()
        }
    }

    private fun getUserImage() {
        val userPhoto = "https://www.bnl.gov/today/body_pics/2017/06/stephanhruszkewycz-hr.jpg"
        userImg.value = userPhoto
    }

    fun getData(callback: HomeRoomCallback) {
        this.callback = callback

        val list = listOf(
            Room(getString(R.string.living_room), R.drawable.living_room),
            Room(getString(R.string.kitchen), R.drawable.kitchen),
            Room(getString(R.string.garden), R.drawable.garden),
            Room(getString(R.string.main_bedroom), R.drawable.main_bedroom),
            Room(getString(R.string.sons_room), R.drawable.sons_room),
            Room(getString(R.string.daughters_room), R.drawable.daughters_room)
        )

        adapter.submitList(list)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Room -> callback.onRoomClick(view.tag as Room)
        else -> Unit
    }
}