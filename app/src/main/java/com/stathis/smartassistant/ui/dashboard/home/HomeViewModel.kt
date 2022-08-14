package com.stathis.smartassistant.ui.dashboard.home

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.callbacks.HomeRoomCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.ui.dashboard.home.adapter.HomeScreenAdapter

class HomeViewModel : ViewModel(), ItemCallback {

    val adapter = HomeScreenAdapter(this)

    private lateinit var callback: HomeRoomCallback

    fun getData(callback: HomeRoomCallback) {
        this.callback = callback
    }

    fun observe(owner: LifecycleOwner) {

    }

    fun release(owner: LifecycleOwner) {

    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Room -> callback.onRoomClick(view.tag as Room)
        else -> Unit
    }
}