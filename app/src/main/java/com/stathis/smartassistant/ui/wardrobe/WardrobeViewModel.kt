package com.stathis.smartassistant.ui.wardrobe

import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.wardrobe.ShoeCategory

class WardrobeViewModel : ViewModel() {

    var event: Event? = null
    var category: ShoeCategory? = null
}