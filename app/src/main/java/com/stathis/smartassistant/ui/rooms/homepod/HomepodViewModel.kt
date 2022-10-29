package com.stathis.smartassistant.ui.rooms.homepod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.util.ROOMS
import timber.log.Timber

class HomepodViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val homepodStateSaved = MutableLiveData<Boolean>()
    val homepodState = MutableLiveData<Boolean>()
    var enabled: Boolean = false

    fun notifyHomepodStateChanged(room: Room, state: Boolean) {
        room.utils.find { it.title == "Μουσική" }?.enabled = state
        enabled = state
        updateLightsInDatabase(room)
    }

    fun updateLightsInDatabase(room: Room) {
        val documentReference = firestore.collection(ROOMS).document(room.title)
        documentReference.set(room).addOnSuccessListener {
            Timber.tag("Firebase").d("Homepod state changed successfully")
            homepodStateSaved.value = true
            homepodState.value = enabled
        }

        documentReference.set(room).addOnFailureListener {
            Timber.tag("Firebase").d("Homepod state did not change")
            homepodStateSaved.value = false
            homepodState.value = !enabled
        }
    }
}