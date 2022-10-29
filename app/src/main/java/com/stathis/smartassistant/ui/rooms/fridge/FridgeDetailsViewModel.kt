package com.stathis.smartassistant.ui.rooms.fridge

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.models.rooms.FridgeDetail
import com.stathis.smartassistant.ui.rooms.fridge.adapter.FridgeDetailsAdapter
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FridgeDetailsViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val fridgeDetails = MutableLiveData<List<FridgeDetail>>()
    val adapter = FridgeDetailsAdapter()

    init {
        getDataFromDb()
    }

    fun getDataFromDb() = viewModelScope.launch(Dispatchers.IO) {
        getFridgeData()
    }

    private suspend fun getFridgeData() {
        val documents = firestore.collection("fridge_details").get().await()
        val list = documents.toListOf<FridgeDetail>()
        fridgeDetails.postValue(list)
    }

    fun observe(owner : LifecycleOwner) {
        fridgeDetails.observe(owner) { data ->
            adapter.submitList(data)
        }
    }

    fun release(owner : LifecycleOwner) {
        fridgeDetails.removeObservers(owner)
    }
}