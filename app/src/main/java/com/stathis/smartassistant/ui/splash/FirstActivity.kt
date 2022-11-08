package com.stathis.smartassistant.ui.splash

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivitySplashBinding
import com.stathis.smartassistant.ui.dashboard.DashboardActivity
import com.stathis.smartassistant.ui.onboarding.OnboardingActivity
import com.stathis.smartassistant.util.COMPLETED_ONBOARDING
import com.stathis.smartassistant.util.SPLASH_SCREEN_LOADING_TIME
import com.stathis.smartassistant.util.readFromSharedPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun init() {
        supportActionBar?.hide()
    }

    override fun startOps() {
        lifecycleScope.launch {
            delay(SPLASH_SCREEN_LOADING_TIME)
            decideNextAction()
        }
    }

    private fun decideNextAction() {
        if (readFromSharedPreferences(COMPLETED_ONBOARDING)) {
            /*
             * The user is redirected to the dashboard screen since he has completed
             * the onboarding descriptive flow.
             */
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            // The user is redirected to the onboarding descriptive flow.
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
    }

    override fun stopOps() {}
}