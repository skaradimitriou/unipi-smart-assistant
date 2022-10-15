package com.stathis.smartassistant.ui.rooms.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.rooms.RoomUtilCallback
import com.stathis.smartassistant.models.RoomUtil
import com.stathis.smartassistant.ui.rooms.utils.adapter.RoomUtilAdapter

class RoomUtilsViewModel : ViewModel(), ItemCallback {

    val adapter = RoomUtilAdapter(this)
    private val utils = MutableLiveData<List<RoomUtil>>()
    private lateinit var callback: RoomUtilCallback

    fun bindList(list: List<RoomUtil>) {
        utils.value = list
    }

    fun observe(owner: LifecycleOwner, callback: RoomUtilCallback) {
        this.callback = callback
        utils.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        utils.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is RoomUtil -> callback.onRoomUtilTap(view.tag as RoomUtil)
        else -> Unit
    }
}