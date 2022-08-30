package com.stathis.smartassistant.ui.events.result

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentResultBinding
import com.stathis.smartassistant.ui.events.EventsViewModel


class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {

    private val viewModel : EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_result_title)
    }

    override fun startOps() {
        binding.nextButton.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun stopOps() {
        //
    }
}