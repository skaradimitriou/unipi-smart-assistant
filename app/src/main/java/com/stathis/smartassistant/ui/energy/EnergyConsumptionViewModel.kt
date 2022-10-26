package com.stathis.smartassistant.ui.energy

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.stathis.smartassistant.models.EnergyModel
import com.stathis.smartassistant.ui.energy.adapter.EnergyAdapter
import com.stathis.smartassistant.util.ENERGY
import com.stathis.smartassistant.util.SEQ
import com.stathis.smartassistant.util.toListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EnergyConsumptionViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val adapter = EnergyAdapter()
    val data = MutableLiveData<List<EnergyModel>>()
    val dataset = MutableLiveData<List<BarEntry>>()

    init {
        getChartData()
    }

    fun getChartData() {
        viewModelScope.launch(Dispatchers.IO) {
            getEnergyConsumption()
        }
    }

    private suspend fun getEnergyConsumption() {
        val documents = firestore.collection(ENERGY)
            .orderBy(SEQ, Query.Direction.ASCENDING)
            .get().await()

        val list = documents.toListOf<EnergyModel>()
        //convert list values to dataset for the view's chart
        val set: List<BarEntry> = list.map { BarEntry(it.seq.toFloat(), it.value.toFloat()) }

        data.postValue(list)
        dataset.postValue(set)
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner) { list ->
            adapter.submitList(list)
        }
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
        dataset.removeObservers(owner)
    }
}