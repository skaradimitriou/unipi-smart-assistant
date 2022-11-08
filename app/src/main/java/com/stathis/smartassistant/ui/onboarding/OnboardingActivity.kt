package com.stathis.smartassistant.ui.onboarding

import android.content.Intent
import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityOnboardingBinding
import com.stathis.smartassistant.ui.dashboard.DashboardActivity
import com.stathis.smartassistant.util.COMPLETED_ONBOARDING
import com.stathis.smartassistant.util.readFromSharedPreferences
import com.stathis.smartassistant.util.saveToSharedPreferences

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val viewModel: OnboardingViewModel by viewModels()

    override fun init() {
        supportActionBar?.hide()
        binding.viewModel = viewModel

        if (readFromSharedPreferences(COMPLETED_ONBOARDING)) {
            goToHomepage()
        }
    }

    override fun startOps() {
        binding.nextBtn.setOnClickListener {
            when (binding.onboardingViewpager.currentItem) {
                in 0..4 -> {
                    binding.onboardingViewpager.currentItem =
                        binding.onboardingViewpager.currentItem + 1
                }
                else -> Unit
            }
        }

        binding.getStartedBtn.setOnClickListener {
            saveOnboardingCompletion()
            goToHomepage()
        }
    }

    override fun stopOps() {}

    /**
     * Saves to shared prefs that the user has completed his onboarding.
     */

    private fun saveOnboardingCompletion() {
        saveToSharedPreferences(
            key = COMPLETED_ONBOARDING,
            value = true
        )
    }

    private fun goToHomepage() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}