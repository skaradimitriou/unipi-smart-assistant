package com.stathis.smartassistant.ui.dashboard.planner

import android.view.View
import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.callbacks.EventsCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.TransportationOption
import com.stathis.smartassistant.ui.dashboard.planner.adapter.PlannerAdapter

class PlannerViewModel : ViewModel(), ItemCallback {

    val adapter = PlannerAdapter(this)
    private lateinit var callback: EventsCallback

    init {
        populateDummyList()
    }

    fun onEventTap(callback: EventsCallback) {
        this.callback = callback
    }

    private fun populateDummyList() {
        //FIXME: Either populate with fixed data or connect to a db later

        val list = listOf(
            Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            ), Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            ), Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            ), Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            ), Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            ), Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            ), Event(
                "Όνομα Συμβάντος",
                "19/09/2022",
                "9:00",
                TransportationOption("Μετακίνηση με αυτοκίνητο", 0),
            )
        )

        adapter.submitList(list)
    }

    override fun onItemTap(view: View) {
        when (view.tag) {
            is Event -> callback.onEventsClick(view.tag as Event)
        }
    }
}