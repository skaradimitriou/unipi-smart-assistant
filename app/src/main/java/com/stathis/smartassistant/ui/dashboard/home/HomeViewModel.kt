package com.stathis.smartassistant.ui.dashboard.home

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.HomeScreenCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo
import com.stathis.smartassistant.models.rooms.GeneralUtil
import com.stathis.smartassistant.models.rooms.GeneralUtilsParent
import com.stathis.smartassistant.ui.dashboard.home.adapter.HomeAdapter
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class HomeViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = HomeAdapter(this)
    val userImg = MutableLiveData<String>()
    val generalUtils = MutableLiveData<List<GeneralUtil>>()

    private lateinit var callback: HomeScreenCallback

    init {
        viewModelScope.launch {
            getUserImage()
        }
    }

    fun getHomeUtils() {
        viewModelScope.launch(Dispatchers.IO) {
            getGeneralUtils()
        }
    }

    private suspend fun getGeneralUtils() {
        val documents = firestore.collection("general_utils").get().await()
        val list = documents.toListOf<GeneralUtil>()
        generalUtils.postValue(list)
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

    fun observe(owner: LifecycleOwner) {
        generalUtils.observe(owner) { list ->
            val containsUtils = adapter.currentList.any { it is GeneralUtilsParent }
            Timber.d("contains model => $containsUtils")

            //FIXME: Refactor this later on

            if (containsUtils) {
                try {
                    val model = adapter.currentList[2] as GeneralUtilsParent
                    model.utils = list
                    adapter.notifyItemChanged(2)
                } catch (e: Exception) {

                }
            } else {
                val currentList = adapter.currentList.toMutableList()
                val model = GeneralUtilsParent(title = "Συντομεύσεις", utils = list)
                currentList.add(model)
                adapter.submitList(currentList)
            }
        }
    }

    fun updateEnabledState(util: GeneralUtil) {
        util.enabled = !util.enabled
        val reference = firestore.collection("general_utils").document(util.title)
        reference.set(util).addOnSuccessListener {
            getHomeUtils()
        }
        reference.set(util).addOnFailureListener {
            Timber.d("FAIL")
        }

    }

    fun release(owner: LifecycleOwner) = generalUtils.removeObservers(owner)

    override fun onItemTap(view: View) = when (view.tag) {
        is RoomPromo -> callback.onRoomsPromoClick(view.tag as RoomPromo)
        is SmartLockerPromo -> callback.onSmartLockerClick(view.tag as SmartLockerPromo)
        is GeneralUtil -> callback.onHomeUtilClick(view.tag as GeneralUtil)
        else -> Unit
    }
}