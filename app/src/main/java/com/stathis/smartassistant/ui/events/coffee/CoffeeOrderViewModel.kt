package com.stathis.smartassistant.ui.events.coffee

import android.app.Application
import android.view.View
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.CoffeeItemCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.Coffee
import com.stathis.smartassistant.ui.events.coffee.adapter.CoffeeOrderAdapter

class CoffeeOrderViewModel(val app : Application) : BaseViewModel(app), ItemCallback {

    val adapter = CoffeeOrderAdapter(this)
    private lateinit var callback : CoffeeItemCallback


    fun bindList(callback: CoffeeItemCallback) {
        this.callback = callback
        
        val list = listOf(
            Coffee(getString(R.string.freddo_espresso), R.drawable.coffee_island),
            Coffee(getString(R.string.freddo_cappuccino), R.drawable.coffee_island),
            Coffee(getString(R.string.espresso), R.drawable.coffee_island),
            Coffee(getString(R.string.cappuccino), R.drawable.coffee_island),
            Coffee(getString(R.string.latte), R.drawable.coffee_island),
            Coffee(getString(R.string.nes), R.drawable.coffee_island),
            Coffee(getString(R.string.flat_white), R.drawable.coffee_island),
            Coffee(getString(R.string.frappe), R.drawable.coffee_island),
            Coffee(getString(R.string.filtered_v60), R.drawable.coffee_island),
            Coffee(getString(R.string.greek_coffee), R.drawable.coffee_island)
        )

        adapter.submitList(list)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Coffee -> callback.onCoffeeTap(view.tag as Coffee)
        else -> Unit
    }
}