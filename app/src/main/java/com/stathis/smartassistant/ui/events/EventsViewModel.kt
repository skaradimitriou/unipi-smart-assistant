package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.*
import com.stathis.smartassistant.util.toUiModel
import timber.log.Timber

class EventsViewModel(val app: Application) : BaseViewModel(app) {

    val screenTitle = MutableLiveData<String>()
    var eventTitle: String? = null
    var eventTime: String? = null
    var eventDate: String? = null
    var eventLocation: String? = null
    var parkingData: ParkingInfo? = null
    var selectedShop: CoffeeShop? = null
    var selectedCoffee: Coffee? = null
    var transportationOption: TransportationOption? = null

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    val eventSaved = MutableLiveData<Boolean>()

    fun getEvent() = Event(
        title = eventTitle.toUiModel(),
        date = eventDate.toUiModel(),
        time = eventTime.toUiModel(),
        location = eventLocation.toUiModel(),
        transportationOption = transportationOption.toUiModel(),
        parkingInfo = parkingData,
        shop = selectedShop,
        coffee = selectedCoffee,
    )

    fun saveEventToDatabase() {
        val event = getEvent()
        val documentReference = firestore.collection("events").document(event.title)

        documentReference.set(event).addOnSuccessListener {
            Timber.tag("Firebase").d("$event added successfully")
            eventSaved.value = true
        }

        documentReference.set(event).addOnFailureListener {
            Timber.tag("Firebase").d("$event failed to be added")
            eventSaved.value = false
        }
    }
}