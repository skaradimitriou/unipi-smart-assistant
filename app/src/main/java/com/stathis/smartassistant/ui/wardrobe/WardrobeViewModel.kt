package com.stathis.smartassistant.ui.wardrobe

import androidx.lifecycle.ViewModel
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.models.wardrobe.AddressInfo
import com.stathis.smartassistant.models.wardrobe.Eshop
import com.stathis.smartassistant.models.wardrobe.ShoeCategory
import com.stathis.smartassistant.models.wardrobe.ShoesToBuy

class WardrobeViewModel : ViewModel() {

    var event: Event? = null
    var category: ShoeCategory? = null
    var purchaseShoes: ShoesToBuy? = null
    var eshop: Eshop? = null

    val addressInfo = AddressInfo(
        firstName = "Test",
        lastName =  "Testopoulos",
        address = "Μπουμπουλίνας 1, Χαλάνδρι, 15231"
    )
}