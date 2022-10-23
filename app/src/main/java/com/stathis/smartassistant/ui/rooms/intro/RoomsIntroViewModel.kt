package com.stathis.smartassistant.ui.rooms.intro

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.rooms.RoomsCallback
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.ui.rooms.intro.adapter.RoomsAdapter
import com.stathis.smartassistant.util.ROOMS
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RoomsIntroViewModel : ViewModel(), ItemCallback {

    val adapter = RoomsAdapter(this)
    private val firestore = FirebaseFirestore.getInstance()
    val rooms = MutableLiveData<List<Room>>()
    private lateinit var callback: RoomsCallback

    fun getRooms() {
        viewModelScope.launch(Dispatchers.IO) {
            getRoomsFromDb()
        }
    }

    private suspend fun getRoomsFromDb() {
        val documents = firestore.collection(ROOMS).get().await()
        val list = documents.toListOf<Room>()
        rooms.postValue(list)
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