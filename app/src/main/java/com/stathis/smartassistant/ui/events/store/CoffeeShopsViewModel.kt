package com.stathis.smartassistant.ui.events.store

import android.app.Application
import android.view.View
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.callbacks.CoffeeShopsCallback
import com.stathis.smartassistant.callbacks.ItemCallback
import com.stathis.smartassistant.models.CoffeeShop
import com.stathis.smartassistant.ui.events.store.adapter.CoffeeShopsAdapter

class CoffeeShopsViewModel(val app: Application) : BaseViewModel(app), ItemCallback {

    val adapter = CoffeeShopsAdapter(this)
    private lateinit var callback: CoffeeShopsCallback

    fun bindList(callback: CoffeeShopsCallback) {
        this.callback = callback

        val list = listOf(
            CoffeeShop(getString(R.string.coffee_island), R.drawable.coffee_island),
            CoffeeShop(getString(R.string.coffee_lab), R.drawable.coffee_lab),
            CoffeeShop(getString(R.string.coffee_hoop), R.drawable.coffee_hoop),
            CoffeeShop(getString(R.string.gregorys), R.drawable.gregorys),
            CoffeeShop(getString(R.string.mikel), R.drawable.mikel),
            CoffeeShop(getString(R.string.coffee_berry), R.drawable.coffee_berry)
        )

        list.map {
            val bestSolution = list.minByOrNull { it.distance }
            bestSolution?.isBestOption = true
        }

        adapter.submitList(list)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is CoffeeShop -> callback.onShopTap(view.tag as CoffeeShop)
        else -> Unit
    }
}