package com.stathis.smartassistant.ui.wardrobe.eshops

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.EshopsCallback
import com.stathis.smartassistant.models.wardrobe.Eshop
import com.stathis.smartassistant.ui.wardrobe.eshops.adapter.EshopsAdapter
import com.stathis.smartassistant.util.ESHOPS
import timber.log.Timber

class EshopsViewModel : ViewModel(), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = EshopsAdapter(this)
    val eshops = MutableLiveData<List<Eshop>>()
    private lateinit var callback: EshopsCallback

    init {
        getEshops()
    }

    fun getEshops() {
        firestore.collection(ESHOPS).get()
            .addOnSuccessListener { docs ->
                val list = mutableListOf<Eshop>()
                for (document in docs) {
                    Timber.d("${document.id} => ${document.data}")
                    val json = Gson().toJson(document.data)
                    val data = Gson().fromJson(json, Eshop::class.java)
                    list.add(data)
                }
                eshops.value = list
            }.addOnFailureListener {
                Timber.d("Error getting documents - $it")
                eshops.value = listOf()
            }
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