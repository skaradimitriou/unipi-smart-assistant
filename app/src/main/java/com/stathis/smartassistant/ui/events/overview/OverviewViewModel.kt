package com.stathis.smartassistant.ui.events.overview

import android.app.Application
import android.view.View
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.dashboard.planner.adapter.PlannerAdapter

class OverviewViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = PlannerAdapter(this)


    override fun onItemTap(view: View) = when (view.tag) {
        is Event -> { }
        else -> Unit
    }
}