package com.stathis.smartassistant.ui.feed

import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.models.feeding.FeedingType
import com.stathis.smartassistant.models.pets.Pet

class FeedingViewModel : ViewModel() {

    var pet: Pet? = null
    var feedType: FeedingType? = null
}