package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.*
import com.stathis.smartassistant.util.NOTIFICATIONS
import com.stathis.smartassistant.util.createNotification
import com.stathis.smartassistant.util.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.*

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

            saveNotificationToDb(title = event.title, date = event.date)
        }

        documentReference.set(event).addOnFailureListener {
            Timber.tag("Firebase").d("$event failed to be added")
            eventSaved.value = false
        }
    }

    private fun saveNotificationToDb(title: String, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val notification = createNotification(
                title = getString(R.string.new_event),
                description = getString(R.string.event_notification_desc).format(title, date),
                category = NotificationType.EVENT
            )

            firestore.collection(NOTIFICATIONS).add(notification).await()
        }
    }
}