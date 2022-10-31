package com.stathis.smartassistant.ui.rooms.general

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.util.ROOMS

class GeneralUtilViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val stateSaved = MutableLiveData<Boolean>()
    val state = MutableLiveData<Boolean>()
    var enabled: Boolean = false

    fun notifyStateChanged(room: Room, util: String, state: Boolean) {
        room.utils.find { it.title == util }?.enabled = state
        enabled = state
        updateStateInDatabase(room)
    }

    private fun updateStateInDatabase(room: Room) {
        val documentReference = firestore.collection(ROOMS).document(room.title)
        documentReference.set(room).addOnSuccessListener {
            stateSaved.value = true
            state.value = enabled
        }

        documentReference.set(room).addOnFailureListener {
            stateSaved.value = false
            state.value = !enabled
        }
    }
}