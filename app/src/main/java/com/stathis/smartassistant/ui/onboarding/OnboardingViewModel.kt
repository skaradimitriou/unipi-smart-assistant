package com.stathis.smartassistant.ui.onboarding

import android.app.Application
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseViewModel
import com.stathis.smartassistant.models.OnboardingItem
import com.stathis.smartassistant.ui.onboarding.adapter.OnboardingAdapter

class OnboardingViewModel(app: Application) : BaseViewModel(app) {

    val adapter = OnboardingAdapter()

    init {
        createList()
    }

    private fun createList() {
        val list = listOf(
            OnboardingItem(
                R.drawable.onboarding_home,
                getString(R.string.onboarding_home_title),
                getString(R.string.onboarding_home_description)
            ),
            OnboardingItem(
                R.drawable.onboarding_planner,
                getString(R.string.onboarding_planner_title),
                getString(R.string.onboarding_planner_description)
            ),
            OnboardingItem(
                R.drawable.onboarding_wardrobe,
                getString(R.string.onboarding_wardrobe_title),
                getString(R.string.onboarding_wardrobe_description)
            ),
            OnboardingItem(
                R.drawable.onboarding_pets,
                getString(R.string.onboarding_pet_title),
                getString(R.string.onboarding_pet_description)
            ),
            OnboardingItem(
                R.drawable.onboarding_notifications,
                getString(R.string.onboarding_notification_title),
                getString(R.string.onboarding_notification_description)
            ),
            OnboardingItem(
                R.drawable.onboarding_charts,
                getString(R.string.onboarding_energy_title),
                getString(R.string.onboarding_energy_description)
            )
        )
        adapter.submitList(list)
    }
}