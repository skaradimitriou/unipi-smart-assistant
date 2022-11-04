package com.stathis.smartassistant.ui.feed.quantity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FeedingQuantityViewModel : ViewModel() {

    val counter: LiveData<Int>
        get() = _counter

    val _counter = MutableLiveData(100)

    var mCounter = 100

    fun increase() {
        if (mCounter < 800) {
            mCounter += 100
            _counter.value = mCounter
        }
    }

    fun decrease() {
        if (mCounter > 100) {
            mCounter -= 100
            _counter.value = mCounter
        }
    }
}