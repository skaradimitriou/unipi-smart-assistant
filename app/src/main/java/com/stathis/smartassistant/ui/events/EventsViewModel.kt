package com.stathis.smartassistant.ui.events

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel

class EventsViewModel(val app : Application) : BaseViewModel(app) {

    val screenTitle = MutableLiveData<String>()

    init {
        screenTitle.value = getString(R.string.events_intro_title)
    }
}