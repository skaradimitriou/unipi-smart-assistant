package com.stathis.smartassistant.ui.dashboard

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.stathis.smartassistant.models.Notification
import com.stathis.smartassistant.ui.dashboard.notifications.adapter.NotificationsAdapter
import com.stathis.smartassistant.util.NOTIFICATIONS
import com.stathis.smartassistant.util.TIMESTAMP
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DashboardViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    var job: Job? = null
    val notifications = MutableLiveData<List<Notification>>()
    val unreadNotifications = MutableLiveData<Int>()
    val readNotifications = MutableLiveData<Boolean>()
    val adapter = NotificationsAdapter()

    /**
     * This method is a polling method with an interval of 20 sec.
     * Its purpose is to keep the user notified about his incoming notifications.
     */

    fun getNotifications() {
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                getNotificationsFromDb()
                delay(20000)
            }
        }
    }

    private suspend fun getNotificationsFromDb() {
        val documents = firestore.collection(NOTIFICATIONS)
            .orderBy(TIMESTAMP, Query.Direction.DESCENDING)
            .get().await()

        val list = documents.toListOf<Notification>()
        val unreadMessages = list.count { !it.hasBeenRead }

        notifications.postValue(list)
        unreadNotifications.postValue(unreadMessages)
    }

    fun observe(owner: LifecycleOwner) {
        notifications.observe(owner) { list ->
            adapter.submitList(list)
        }
    }

    fun readAllNotifications() {
        val notifications = notifications.value
        notifications?.forEach { it.hasBeenRead = true }
    }

    override fun onCleared() {
        job?.cancel()
    }
}