package com.stathis.smartassistant.ui.rooms.intro

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.rooms.RoomsCallback
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.ui.rooms.intro.adapter.RoomsAdapter
import com.stathis.smartassistant.util.ROOMS
import timber.log.Timber

class RoomsIntroViewModel : ViewModel(), ItemCallback {

    val adapter = RoomsAdapter(this)
    private val firestore = FirebaseFirestore.getInstance()
    val rooms = MutableLiveData<List<Room>>()
    private lateinit var callback: RoomsCallback

    fun getRooms() {
        firestore.collection(ROOMS).get().addOnSuccessListener { docs ->
            val list = mutableListOf<Room>()
            for (document in docs) {
                val json = Gson().toJson(document.data)
                val data = Gson().fromJson(json, Room::class.java)
                list.add(data)
            }
            rooms.value = list.sortedBy { it.sequence }
        }.addOnFailureListener {
            Timber.d("Error getting documents - $it")
            rooms.value = listOf()
        }
    }

    fun observe(owner: LifecycleOwner, callback: RoomsCallback) {
        this.callback = callback
        rooms.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        rooms.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Room -> callback.onRoomTap(view.tag as Room)
        else -> Unit
    }
}