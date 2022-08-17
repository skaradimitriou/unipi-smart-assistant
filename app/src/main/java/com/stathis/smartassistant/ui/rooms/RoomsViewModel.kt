package com.stathis.smartassistant.ui.rooms

import android.app.Application
import android.view.View
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.RoomUtil
import com.stathis.smartassistant.ui.rooms.adapter.RoomUtilAdapter

class RoomsViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = RoomUtilAdapter(this)

    init {
        getDummyData()
    }

    fun getDummyData() {
        //FIXME: Load final list

        val dummyList = listOf(
            RoomUtil("Util #1"),
            RoomUtil("Util #2"),
            RoomUtil("Util #3"),
            RoomUtil("Util #4"),
            RoomUtil("Util #5"),
            RoomUtil("Util #6"),
            RoomUtil("Util #7")
        )

        adapter.submitList(dummyList)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is RoomUtil -> {
            //handle click later on
        }
        else -> Unit
    }
}