package com.stathis.smartassistant.ui.events.parking

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentParkingSlotBinding
import com.stathis.smartassistant.ui.events.EventsViewModel

class ParkingSlotFragment : BaseFragment<FragmentParkingSlotBinding>(R.layout.fragment_parking_slot) {

    private val sharedViewModel : EventsViewModel by activityViewModels()

    override fun init() {
        //
    }

    override fun startOps() {
        //
    }

    override fun stopOps() {
        //
    }
}