package com.stathis.smartassistant.ui.faq

import android.view.MenuItem
import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityFaqBinding

class FaqActivity : BaseActivity<ActivityFaqBinding>(R.layout.activity_faq) {

    private val viewModel: FaqViewModel by viewModels()

    override fun init() {
        title = getString(R.string.faq_screen_title)
        binding.viewModel = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        viewModel.observe(this)
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }
}