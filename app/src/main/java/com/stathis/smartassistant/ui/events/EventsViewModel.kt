package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.*
import com.stathis.smartassistant.util.toUiModel

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

    fun getEvent() = Event(
        title = eventTitle.toUiModel(),
        date = eventDate.toUiModel(),
        time = eventTime.toUiModel(),
        transportationOption = transportationOption.toUiModel(),
        parkingInfo = parkingData,
        shop = selectedShop,
        coffee = selectedCoffee,
    )
}