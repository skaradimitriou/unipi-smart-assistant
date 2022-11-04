package com.stathis.smartassistant.ui.dashboard.planner

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.EventsCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.NotificationType
import com.stathis.smartassistant.ui.dashboard.planner.adapter.PlannerAdapter
import com.stathis.smartassistant.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class PlannerViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = PlannerAdapter(this)
    val firestore by lazy { FirebaseFirestore.getInstance() }
    private lateinit var callback: EventsCallback
    val events = MutableLiveData<List<Event>>()
    val randomNumber = Random.nextInt(1, 6)

    val isUserBusy: LiveData<Boolean>
        get() = _isUserBusy

    private val _isUserBusy = MutableLiveData(false)

    fun getUserEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            getEventsFromDb()
        }
    }

    private suspend fun getEventsFromDb() {
        val document = firestore.collection(EVENTS)
            .orderBy(TIMESTAMP, Query.Direction.DESCENDING)
            .get().await()

        val list = document.toListOf<Event>()

        /*
         * Filtering list from firestore to get today's events only
         * If user has more than 3 events, he is considered busy and thus he should
         * see the feed-a-pet notification in the view layer
         */

        val myList = list.filter { it.date == getCurrentDate() }
        val willBeBusy = myList.size >= 3

        events.postValue(myList)
        _isUserBusy.postValue(willBeBusy)
    }

    fun notifyAboutPetStatus() {
        if (randomNumber == 3 || randomNumber == 5) {
            viewModelScope.launch(Dispatchers.IO) {
                val notification = createNotification(
                    title = getString(R.string.pet_status_notification_title),
                    description = getString(R.string.pet_status_notification_desc),
                    category = NotificationType.PETS
                )

                firestore.collection(NOTIFICATIONS).add(notification).await()
            }
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