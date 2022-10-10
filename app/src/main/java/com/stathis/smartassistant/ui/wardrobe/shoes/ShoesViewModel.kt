package com.stathis.smartassistant.ui.wardrobe.shoes

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.ShoesCallback
import com.stathis.smartassistant.models.wardrobe.ShoeCategory
import com.stathis.smartassistant.models.wardrobe.Shoes
import com.stathis.smartassistant.ui.wardrobe.shoes.adapter.ShoesAdapter
import com.stathis.smartassistant.util.SHOES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ShoesViewModel : ViewModel(), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = ShoesAdapter(this)
    val shoesList = MutableLiveData<List<Shoes>>()
    private lateinit var callback: ShoesCallback

    fun getShoes(categoryName : String) {
        firestore.collection(SHOES).whereEqualTo("category", categoryName).get().addOnSuccessListener { docs ->
            val list = mutableListOf<Shoes>()
            for (document in docs) {
                Timber.d("${document.id} => ${document.data}")
                val json = Gson().toJson(document.data)
                val data = Gson().fromJson(json, Shoes::class.java)
                list.add(data)
            }
            shoesList.value = list
        }.addOnFailureListener {
            Timber.d("Error getting documents - $it")
            shoesList.value = listOf()
        }
    }

    fun observe(owner: LifecycleOwner, callback: ShoesCallback) {
        this.callback = callback
        shoesList.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        shoesList.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Shoes -> callback.onShoesTap(view.tag as Shoes)
        else -> Unit
    }
}