package com.stathis.smartassistant.ui.dashboard.home

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.HomeScreenCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo
import com.stathis.smartassistant.ui.dashboard.home.adapter.HomeAdapter
import kotlinx.coroutines.launch

class HomeViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = HomeAdapter(this)
    val userImg = MutableLiveData<String>()

    private lateinit var callback: HomeScreenCallback

    init {
        viewModelScope.launch {
            getUserImage()
        }
    }

    private fun getUserImage() {
        val userPhoto = "https://www.bnl.gov/today/body_pics/2017/06/stephanhruszkewycz-hr.jpg"
        userImg.value = userPhoto
    }

    fun addListener(callback: HomeScreenCallback) {
        this.callback = callback

        val list = listOf(
            RoomPromo(
                "Δωμάτια",
                "Διαχειρίσου τις λειτουργίες ενός δωματίου εύκολα και γρήγορα"
            ),
            SmartLockerPromo(
                "Έξυπνη Παπουτσοθήκη",
                "Έχεις έξοδο; Βρές το κατάλληλο παππούτσι αμέσως!"
            )
        )
        adapter.submitList(list)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is RoomPromo -> callback.onRoomsPromoClick(view.tag as RoomPromo)
        is SmartLockerPromo -> callback.onSmartLockerClick(view.tag as SmartLockerPromo)
        else -> Unit
    }
}