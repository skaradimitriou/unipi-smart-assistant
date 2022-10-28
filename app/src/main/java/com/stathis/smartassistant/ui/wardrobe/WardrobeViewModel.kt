package com.stathis.smartassistant.ui.wardrobe

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.NotificationType
import com.stathis.smartassistant.models.wardrobe.AddressInfo
import com.stathis.smartassistant.models.wardrobe.Eshop
import com.stathis.smartassistant.models.wardrobe.ShoeCategory
import com.stathis.smartassistant.models.wardrobe.ShoesToBuy
import com.stathis.smartassistant.util.NOTIFICATIONS
import com.stathis.smartassistant.util.createNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WardrobeViewModel(val app: Application) : BaseViewModel(app) {

    private val firestore = FirebaseFirestore.getInstance()
    var event: Event? = null
    var category: ShoeCategory? = null
    var purchaseShoes: ShoesToBuy? = null
    var eshop: Eshop? = null

    val addressInfo = AddressInfo(
        firstName = "Test",
        lastName = "Testopoulos",
        address = "Μπουμπουλίνας 1, Χαλάνδρι, 15231"
    )

    fun saveShoeOrder(shoeName: String, eshopName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val notification = createNotification(
                title = getString(R.string.smart_wardrobe),
                description = getString(R.string.wardrobe_order_notification_desc).format(shoeName, eshopName),
                category = NotificationType.WARDROBE
            )

            firestore.collection(NOTIFICATIONS).add(notification)
        }
    }
}