package com.stathis.smartassistant.ui.details

import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.events.overview.adapter.EventOverviewAdapter

class EventInfoViewModel() : ViewModel() {

    val adapter = EventOverviewAdapter()

    fun bindModel(event: Event) {
        val list = listOf(event)
        adapter.submitList(list)
    }
}