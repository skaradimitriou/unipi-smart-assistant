package com.stathis.smartassistant.ui.rooms

import android.app.Application
import android.view.View
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.RoomUtilsCallback
import com.stathis.smartassistant.models.RoomUtil
import com.stathis.smartassistant.ui.rooms.adapter.RoomUtilAdapter

class RoomsViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = RoomUtilAdapter(this)
    private lateinit var callback: RoomUtilsCallback

    init {
        getDummyData()
    }

    fun onRoomUtilTap(callback: RoomUtilsCallback) {
        this.callback = callback
    }

    fun getDummyData() {
        //FIXME: Load final list of features

        val dummyList = listOf(
            RoomUtil("Διαχείριση Φωτισμού"),
            RoomUtil("Θερμοκρασία"),
            RoomUtil("Σκούπισμα (Έξυπνη Σκούπα)"),
            RoomUtil("Σφουγγάρισμα (Έξυπνη Σκούπα)"),
        )

        adapter.submitList(dummyList)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is RoomUtil -> callback.onRoomUtilTap(view.tag as RoomUtil)
        else -> Unit
    }
}