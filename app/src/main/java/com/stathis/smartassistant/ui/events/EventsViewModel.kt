package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.TransportationOption

class EventsViewModel(val app : Application) : BaseViewModel(app) {

    val screenTitle = MutableLiveData<String>()
    var eventTitle : String? = null
}