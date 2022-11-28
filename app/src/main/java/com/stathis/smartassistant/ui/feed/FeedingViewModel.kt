package com.stathis.smartassistant.ui.feed

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.NotificationType
import com.stathis.smartassistant.models.feeding.FeedingType
import com.stathis.smartassistant.models.pets.Pet
import com.stathis.smartassistant.util.NOTIFICATIONS
import com.stathis.smartassistant.util.createNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FeedingViewModel(val app: Application) : BaseViewModel(app) {

    private val firestore = FirebaseFirestore.getInstance()

    var pet: Pet? = null
    var feedType: FeedingType? = null

    fun saveNotificationToDb() {
        val description = when (feedType) {
            FeedingType.WATER_NOW -> getString(R.string.gave_water)
            FeedingType.WATER_LATER -> getString(R.string.scheduled_water)
            FeedingType.FOOD_NOW -> getString(R.string.gave_food)
            FeedingType.FOOD_LATER -> getString(R.string.scheduled_food)
            else -> getString(R.string.empty)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val notification = createNotification(
                title = getString(R.string.feed_notification_title),
                description = getString(R.string.feed_notification_description).format(
                    description,
                    pet?.nickname
                ),
                category = NotificationType.PETS
            )

            firestore.collection(NOTIFICATIONS).add(notification).await()
        }
    }
}