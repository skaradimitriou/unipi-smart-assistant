package com.stathis.smartassistant.ui.rooms.temperature

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.models.Room
import com.stathis.smartassistant.util.ROOMS
import timber.log.Timber

class TemperatureViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val heat = MutableLiveData<Int>()
    val maxHeatReached = MutableLiveData<Boolean>()
    val minHeatReached = MutableLiveData<Boolean>()
    var heatLevel = 21
    var enabled: Boolean = false

    fun increaseHeat(room: Room) {
        if (heatLevel == maxHeatLevel) {
            maxHeatReached.value = true
            //max temperature reached
        } else {
            heatLevel++
            heat.value = heatLevel
            notifyTemperatureLevelChanged(room, heatLevel)
            maxHeatReached.value = false
        }
    }

    fun decreaseHeat(room: Room) {
        if (heatLevel == minHeatLevel) {
            //min temperature reached
            minHeatReached.value = true
        } else {
            heatLevel--
            heat.value = heatLevel
            notifyTemperatureLevelChanged(room, heatLevel)
            minHeatReached.value = false
        }
    }

    fun notifyTemperatureActivated(room: Room, isEnabled: Boolean) {
        room.utils.find { it.title == "Θερμοκρασία" }?.enabled = isEnabled
        enabled = isEnabled
        updateTemperatureInDatabase(room)
    }

    fun notifyTemperatureLevelChanged(room: Room, heatLevel: Int) {
        room.utils.find { it.title == "Θερμοκρασία" }?.heatLevel = heatLevel
        updateTemperatureInDatabase(room)
    }

    fun updateTemperatureInDatabase(room: Room) {
        val documentReference = firestore.collection(ROOMS).document(room.title)
        documentReference.set(room).addOnSuccessListener {
            Timber.tag("Firebase").d("Temperature level changed successfully")
        }

        documentReference.set(room).addOnFailureListener {
            Timber.tag("Firebase").d("Temperature level state did not changed")
        }
    }

    fun release(owner: LifecycleOwner) {
        heat.removeObservers(owner)
        maxHeatReached.removeObservers(owner)
        minHeatReached.removeObservers(owner)
    }

    companion object {
        const val minHeatLevel = 16
        const val maxHeatLevel = 30
    }
}