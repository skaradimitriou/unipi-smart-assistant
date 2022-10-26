package com.stathis.smartassistant.ui.energy

import android.view.MenuItem
import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityEnergyConsumptionBinding
import com.stathis.smartassistant.util.setupChart
import com.stathis.smartassistant.util.toChartLabels

class EnergyConsumptionActivity :
    BaseActivity<ActivityEnergyConsumptionBinding>(R.layout.activity_energy_consumption) {

    private val viewModel: EnergyConsumptionViewModel by viewModels()

    override fun init() {
        title = getString(R.string.energy_management_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(this)

        viewModel.data.observe(this) { list ->
            binding.chart.toChartLabels(data = list)
        }

        viewModel.dataset.observe(this) { dataset ->
            binding.chart.setupChart(list = dataset)
        }
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}