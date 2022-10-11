package com.stathis.smartassistant.ui.wardrobe.purchase

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.ShoesToBuyCallback
import com.stathis.smartassistant.models.wardrobe.ShoesToBuy
import com.stathis.smartassistant.ui.wardrobe.purchase.adapter.PurchaseShoesAdapter
import com.stathis.smartassistant.util.PURCHASE_SHOES
import timber.log.Timber

class PurchaseShoesViewModel : ViewModel(), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = PurchaseShoesAdapter(this)
    val shoesList = MutableLiveData<List<LocalModel>>()
    private lateinit var callback: ShoesToBuyCallback

    fun getShoes(category: String) {
        firestore.collection(PURCHASE_SHOES).whereEqualTo("category", category).get()
            .addOnSuccessListener { docs ->
                val list = mutableListOf<ShoesToBuy>()
                for (document in docs) {
                    val json = Gson().toJson(document.data)
                    val data = Gson().fromJson(json, ShoesToBuy::class.java)
                    list.add(data)
                }
                shoesList.value = list
            }.addOnFailureListener {
                Timber.d("Error getting documents - $it")
                shoesList.value = listOf()
            }
    }

    fun observe(owner: LifecycleOwner, callback: ShoesToBuyCallback) {
        this.callback = callback
        shoesList.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        shoesList.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is ShoesToBuy -> callback.onShoesTap(view.tag as ShoesToBuy)
        else -> Unit
    }
}