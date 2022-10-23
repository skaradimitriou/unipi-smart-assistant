package com.stathis.smartassistant.ui.wardrobe.shoes

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.ShoesCallback
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.wardrobe.AddShoePromo
import com.stathis.smartassistant.models.wardrobe.Shoes
import com.stathis.smartassistant.ui.wardrobe.shoes.adapter.ShoesAdapter
import com.stathis.smartassistant.util.EVENTS
import com.stathis.smartassistant.util.SHOES
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class ShoesViewModel : ViewModel(), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = ShoesAdapter(this)
    val shoesList = MutableLiveData<List<LocalModel>>()
    val shoesAddedToEvent = MutableLiveData<Boolean>()
    private lateinit var callback: ShoesCallback

    fun getShoes(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getShoesFromDb(categoryName)
        }
    }

    private suspend fun getShoesFromDb(categoryName: String) {
        val document = firestore.collection(SHOES)
            .whereEqualTo("category", categoryName)
            .get().await()

        val shoeList = document.toListOf<Shoes>()
        val list = mutableListOf<LocalModel>()
        list.addAll(shoeList)
        list.add(AddShoePromo())
        shoesList.postValue(list)
    }

    fun observe(owner: LifecycleOwner, callback: ShoesCallback) {
        this.callback = callback
        shoesList.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        shoesList.removeObservers(owner)
        shoesAddedToEvent.removeObservers(owner)
    }

    fun updateShoesOnEvent(event: Event, shoes: Shoes) {
        firestore.collection(EVENTS).document(event.title).update(mapOf("shoes" to shoes))
            .addOnSuccessListener { docs ->
                Timber.d("Shoes added to event")
                shoesAddedToEvent.value = true
            }.addOnFailureListener {
                Timber.d("Error getting documents - $it")
                shoesAddedToEvent.value = false
            }
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Shoes -> callback.onShoesTap(view.tag as Shoes)
        is AddShoePromo -> callback.onAddItemTap(view.tag as AddShoePromo)
        else -> Unit
    }
}