package com.stathis.smartassistant.ui.wardrobe.events

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.stathis.smartassistant.callbacks.EventsCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.dashboard.planner.adapter.PlannerAdapter
import com.stathis.smartassistant.util.EVENTS
import timber.log.Timber

class EventsViewModel : ViewModel(), ItemCallback {

    val adapter = PlannerAdapter(this)
    val firestore by lazy { FirebaseFirestore.getInstance() }
    private lateinit var callback: EventsCallback
    val events = MutableLiveData<List<Event>>()

    fun getUserEvents() {
        firestore.collection(EVENTS).get().addOnSuccessListener { docs ->
            val list = mutableListOf<Event>()
            for (document in docs) {
                Timber.d("${document.id} => ${document.data}")
                val json = Gson().toJson(document.data)
                val data = Gson().fromJson(json, Event::class.java)
                list.add(data)
            }
            events.value = list
        }.addOnFailureListener {
            Timber.d("Error getting documents - $it")
            events.value = listOf()
        }
    }

    fun observe(owner: LifecycleOwner, showEmptyScreen: (Boolean) -> Unit) {
        events.observe(owner) {
            adapter.submitList(it)
            showEmptyScreen.invoke(it.isEmpty())
        }
    }

    fun release(owner: LifecycleOwner) {
        events.removeObservers(owner)
    }

    fun onEventTap(callback: EventsCallback) {
        this.callback = callback
    }

    override fun onItemTap(view: View) {
        when (view.tag) {
            is Event -> callback.onEventsClick(view.tag as Event)
        }
    }
}