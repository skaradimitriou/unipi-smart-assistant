package com.stathis.smartassistant.ui.details

import android.view.MenuItem
import androidx.activity.viewModels
import com.google.gson.Gson
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityEventInfoBinding
import com.stathis.smartassistant.models.Event
import com.stathis.smartassistant.util.EVENT

class EventInfoActivity : BaseActivity<ActivityEventInfoBinding>(R.layout.activity_event_info) {

    private val viewModel: EventInfoViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        val event = intent.getStringExtra(EVENT)
        event?.let {
            val model = Gson().fromJson(it, Event::class.java)
            supportActionBar?.title = model.title
            viewModel.bindModel(event = model)
        }
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }
}