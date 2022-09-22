package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.TransportationOption
import com.stathis.smartassistant.util.toUiModel

class EventsViewModel(val app: Application) : BaseViewModel(app) {

    val screenTitle = MutableLiveData<String>()
    var eventTitle: String? = null
    var eventTime : String? = null
    var eventDate : String? = null
    var transportationOption: TransportationOption? = null

    fun getEvent() = Event(
        title = eventTitle.toUiModel(),
        date = "",
        time = "",
        transportationOption = transportationOption.toUiModel()
    )
}