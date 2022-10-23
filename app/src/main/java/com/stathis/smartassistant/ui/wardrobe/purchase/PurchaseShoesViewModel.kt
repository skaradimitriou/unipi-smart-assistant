package com.stathis.smartassistant.ui.wardrobe.purchase

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.abstraction.LocalModel
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.callbacks.wardrobe.ShoesToBuyCallback
import com.stathis.smartassistant.models.wardrobe.ShoesToBuy
import com.stathis.smartassistant.ui.wardrobe.purchase.adapter.PurchaseShoesAdapter
import com.stathis.smartassistant.util.PURCHASE_SHOES
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PurchaseShoesViewModel : ViewModel(), ItemCallback {

    val firestore = FirebaseFirestore.getInstance()
    val adapter = PurchaseShoesAdapter(this)
    val shoesList = MutableLiveData<List<LocalModel>>()
    private lateinit var callback: ShoesToBuyCallback

    fun getShoes(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getShoesFromDb(category)
        }
    }

    private suspend fun getShoesFromDb(category: String) {
        val document = firestore.collection(PURCHASE_SHOES)
            .whereEqualTo("category", category)
            .get().await()
        val list = document.toListOf<ShoesToBuy>()
        shoesList.postValue(list)
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