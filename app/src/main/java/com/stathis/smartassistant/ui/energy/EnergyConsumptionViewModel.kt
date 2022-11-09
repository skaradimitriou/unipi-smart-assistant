package com.stathis.smartassistant.ui.energy


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
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

    /**
     * Data refers to the data being obtained from the firestore db
     */

    val data: LiveData<List<EnergyModel>>
        get() = _data

    private val _data: MutableLiveData<List<EnergyModel>> = MutableLiveData(listOf())

    /**
     * Dataset refers to the data being displayed inside the graph area of the view layer
     */

    val dataset: LiveData<List<BarEntry>>
        get() = _dataset

    private val _dataset: MutableLiveData<List<BarEntry>> = MutableLiveData(listOf())

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

        _data.postValue(list)
        _dataset.postValue(set)
    }

    fun observe(owner: LifecycleOwner) {
        _data.observe(owner) { list ->
            adapter.submitList(list)
        }
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
        _data.removeObservers(owner)
        dataset.removeObservers(owner)
        _dataset.removeObservers(owner)
    }
}