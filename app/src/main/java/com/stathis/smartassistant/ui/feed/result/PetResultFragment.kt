package com.stathis.smartassistant.ui.feed.result

import androidx.fragment.app.activityViewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentPetResultBinding
import com.stathis.smartassistant.ui.feed.FeedingViewModel
import com.stathis.smartassistant.util.setScreenTitle

class PetResultFragment : BaseFragment<FragmentPetResultBinding>(R.layout.fragment_pet_result) {

    private val sharedViewModel: FeedingViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.success))
    }

    override fun startOps() {
        binding.returnToPetsBtn.setOnClickListener {
            sharedViewModel.saveNotificationToDb()
            //returns to pets
            requireActivity().finish()
        }
    }

    override fun stopOps() {}
}