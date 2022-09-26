package com.stathis.smartassistant.ui.events.overview

import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.events.overview.adapter.EventOverviewAdapter

class OverviewViewModel() : ViewModel() {

    val adapter = EventOverviewAdapter()

    fun bindModel(event: Event) {
        val list = listOf(event)
        adapter.submitList(list)
    }
}