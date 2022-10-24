package com.stathis.smartassistant.ui.faq

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Faq
import com.stathis.smartassistant.ui.faq.adapter.FaqAdapter
import com.stathis.smartassistant.util.FAQ
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FaqViewModel : ViewModel(), ItemCallback {

    val adapter = FaqAdapter()
    private val firestore = FirebaseFirestore.getInstance()
    private val faqs = MutableLiveData<List<Faq>>()

    init {
        getFaqs()
    }

    fun getFaqs() {
        viewModelScope.launch(Dispatchers.IO) {
            getFaqsFromDb()
        }
    }

    private suspend fun getFaqsFromDb() {
        val documents = firestore.collection(FAQ).get().await()
        val list = documents.toListOf<Faq>().sortedBy { it.seq }
        faqs.postValue(list)
    }

    fun observe(owner: LifecycleOwner) {
        faqs.observe(owner) { faqs ->
            adapter.submitList(faqs)
        }
    }

    fun release(owner: LifecycleOwner) = faqs.removeObservers(owner)

    override fun onItemTap(view: View) {}
}