package com.stathis.smartassistant.ui.dashboard.planner

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.callbacks.EventsCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.ui.dashboard.planner.adapter.PlannerAdapter
import com.stathis.smartassistant.util.EVENTS
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PlannerViewModel : ViewModel(), ItemCallback {

    val adapter = PlannerAdapter(this)
    val firestore by lazy { FirebaseFirestore.getInstance() }
    private lateinit var callback: EventsCallback
    val events = MutableLiveData<List<Event>>()

    fun getUserEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            getEventsFromDb()
        }
    }

    private suspend fun getEventsFromDb() {
        val document = firestore.collection(EVENTS).get().await()
        val list = document.toListOf<Event>()
        events.postValue(list)
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