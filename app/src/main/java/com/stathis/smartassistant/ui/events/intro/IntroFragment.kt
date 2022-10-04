package com.stathis.smartassistant.ui.events.intro

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentIntroBinding
import com.stathis.smartassistant.ui.events.EventsViewModel
import com.stathis.smartassistant.util.*


class IntroFragment : BaseFragment<FragmentIntroBinding>(R.layout.fragment_intro) {

    private val viewModel: EventsViewModel by activityViewModels()

    override fun init() {
        viewModel.screenTitle.value = getString(R.string.events_intro_title)
    }

    override fun startOps() {
        getDateAndTime(
            currentDate = { date -> binding.eventDateTxtView.text = date },
            currentTime = { time -> binding.eventTimeTxtView.text = time }
        )

        binding.eventDescriptionEditTxt.afterTextChanged { input ->
            binding.eventDescInputField.showErrorMessageIfEmpty(
                errorMessage = getString(R.string.events_error_msg)
            )
        }

        binding.eventLocationEditTxt.afterTextChanged { input ->
            binding.eventLocationInputField.showErrorMessageIfEmpty(
                errorMessage = getString(R.string.events_location_error_msg)
            )
        }

        binding.eventDateTxtView.setOnClickListener {
            //open date selection & bind the result to the screen
            showDatePicker { selectedDate ->
                binding.eventDateTxtView.text = selectedDate
            }
        }

        binding.eventTimeTxtView.setOnClickListener {
            //open time selection & bind the result to the screen
            showTimePicker { selectedTime ->
                binding.eventTimeTxtView.text = selectedTime
            }
        }

        binding.nextButton.setOnClickListener {
            val title = binding.eventDescriptionEditTxt.text.toString()
            val location = binding.eventLocationEditTxt.text.toString()

            if (title.isNotEmpty() && location.isNotEmpty()) {
                viewModel.eventDate = binding.eventDateTxtView.text.toString()
                viewModel.eventTime = binding.eventTimeTxtView.text.toString()
                viewModel.eventTitle = title
                viewModel.eventLocation = location

                goToTransportScreen()
            } else {
                binding.showSnackbar(getString(R.string.events_intro_error_msg))
            }
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the transport screen via safeargs
     */

    private fun goToTransportScreen() {
        val action = IntroFragmentDirections.goToTransportationsScreen()
        findNavController().navigate(action)
    }
}