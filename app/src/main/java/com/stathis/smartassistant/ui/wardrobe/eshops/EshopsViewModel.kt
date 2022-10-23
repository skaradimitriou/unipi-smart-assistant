package com.stathis.smartassistant.ui.wardrobe.eshops

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.EshopsCallback
import com.stathis.smartassistant.models.wardrobe.Eshop
import com.stathis.smartassistant.ui.wardrobe.eshops.adapter.EshopsAdapter
import com.stathis.smartassistant.util.ESHOPS
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EshopsViewModel : ViewModel(), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = EshopsAdapter(this)
    val eshops = MutableLiveData<List<Eshop>>()
    private lateinit var callback: EshopsCallback

    init {
        getEshops()
    }

    fun getEshops() {
        viewModelScope.launch(Dispatchers.IO) {
            getEshopsFromDb()
        }
    }

    private suspend fun getEshopsFromDb() {
        val document = firestore.collection(ESHOPS).get().await()
        val list = document.toListOf<Eshop>()
        eshops.postValue(list)
    }

    fun observe(owner: LifecycleOwner, callback: EshopsCallback) {
        this.callback = callback
        eshops.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        eshops.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Eshop -> callback.onEshopTap(view.tag as Eshop)
        else -> Unit
    }
}