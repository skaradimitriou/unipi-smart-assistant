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
        //FIXME: Add promo onboarding items

        val list = listOf(
            OnboardingItem(
                R.mipmap.smarty_logo,
                getString(R.string.app_name),
                getString(R.string.app_name)
            ),
            OnboardingItem(
                R.mipmap.smarty_logo,
                getString(R.string.app_name),
                getString(R.string.app_name)
            ),
            OnboardingItem(
                R.mipmap.smarty_logo,
                getString(R.string.app_name),
                getString(R.string.app_name)
            ),
            OnboardingItem(
                R.mipmap.smarty_logo,
                getString(R.string.app_name),
                getString(R.string.app_name)
            ),
            OnboardingItem(
                R.mipmap.smarty_logo,
                getString(R.string.app_name),
                getString(R.string.app_name)
            )
        )
        adapter.submitList(list)
    }
}