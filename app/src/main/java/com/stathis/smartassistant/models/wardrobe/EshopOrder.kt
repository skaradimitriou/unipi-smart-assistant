package com.stathis.smartassistant.models.wardrobe

data class EshopOrder(
    val shoes : ShoesToBuy,
    val eshop : Eshop,
    val addressInfo : AddressInfo
)