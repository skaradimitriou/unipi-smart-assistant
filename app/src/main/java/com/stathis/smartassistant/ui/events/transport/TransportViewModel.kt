package com.stathis.smartassistant.ui.events.transport

import android.app.Application
import android.view.View
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.TransportOptionCallback
import com.stathis.smartassistant.models.TransportationOption
import com.stathis.smartassistant.ui.events.transport.adapter.TransportationAdapter

class TransportViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = TransportationAdapter(this)
    private lateinit var callback: TransportOptionCallback

    init {
        getData()
    }

    private fun getData() {
        val list = listOf(
            TransportationOption(getString(R.string.transport_train), R.drawable.living_room),
            TransportationOption(getString(R.string.transport_car), R.drawable.living_room),
            TransportationOption(getString(R.string.transport_bus), R.drawable.living_room),
            TransportationOption(getString(R.string.transport_bike), R.drawable.living_room),
            TransportationOption(getString(R.string.transport_walk), R.drawable.living_room)
        )
        adapter.submitList(list)
    }

    fun onTransportOptionTap(callback: TransportOptionCallback) {
        this.callback = callback
    }

    override fun onItemTap(view: View) {
        when (view.tag) {
            is TransportationOption -> callback.onOptionTap(view.tag as TransportationOption)
        }
    }
}