package com.stathis.smartassistant.ui.rooms.lights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.util.ROOMS
import timber.log.Timber

class LightsManagementViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val lightsStateSaved = MutableLiveData<Boolean>()
    val lightsState = MutableLiveData<Boolean>()
    var enabled: Boolean = false

    fun notifyLightsModeChanged(room: Room, lightState: Boolean) {
        room.utils.find { it.title == "Φωτισμός" }?.enabled = lightState
        enabled = lightState
        updateLightsInDatabase(room)
    }

    fun updateLightsInDatabase(room: Room) {
        val documentReference = firestore.collection(ROOMS).document(room.title)
        documentReference.set(room).addOnSuccessListener {
            Timber.tag("Firebase").d("Lights state changed successfully")
            lightsStateSaved.value = true
            lightsState.value = enabled
        }

        documentReference.set(room).addOnFailureListener {
            Timber.tag("Firebase").d("Lights state did not changed")
            lightsStateSaved.value = false
            lightsState.value = !enabled
        }
    }
}