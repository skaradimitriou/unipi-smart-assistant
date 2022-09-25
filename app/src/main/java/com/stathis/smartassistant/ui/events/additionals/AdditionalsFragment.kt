package com.stathis.smartassistant.ui.events.additionals

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentAdditionalsBinding
import com.stathis.smartassistant.ui.events.EventsViewModel


class AdditionalsFragment : BaseFragment<FragmentAdditionalsBinding>(R.layout.fragment_additionals) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_additionals_title)
    }

    override fun startOps() {
        binding.pickStoreButton.setOnClickListener {

        }

        binding.notNowButton.setOnClickListener {

        }
    }

    override fun stopOps() {
        //
    }
}