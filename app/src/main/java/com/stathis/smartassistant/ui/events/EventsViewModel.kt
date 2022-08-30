package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.stathis.smartassistant.abstraction.BaseViewModel

class EventsViewModel(val app : Application) : BaseViewModel(app) {

    val screenTitle = MutableLiveData<String>()
}