package com.stathis.smartassistant.ui.rooms.homepod

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentHomepodBinding
import com.stathis.smartassistant.util.setScreenTitle
import com.stathis.smartassistant.util.showSnackbar

class HomepodFragment : BaseFragment<FragmentHomepodBinding>(R.layout.fragment_homepod) {

    private val viewModel: HomepodViewModel by viewModels()
    private val args: HomepodFragmentArgs by navArgs()

    override fun init() {
        setScreenTitle(getString(R.string.homepod_screen_title))
        binding.homepodActiveToggle.isChecked = args.util.enabled
        binding.songInProgress = args.util.enabled
    }

    override fun startOps() {
        binding.homepodActiveToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.notifyHomepodStateChanged(room = args.room, state = isChecked)
        }

        binding.songPlayingCard.setOnClickListener {
            goToMusicScreen()
        }

        viewModel.homepodState.observe(viewLifecycleOwner) { isActive ->
            if (isActive) {
                binding.showSnackbar(getString(R.string.homepod_on))
                binding.songInProgress = true
            } else {
                binding.showSnackbar(getString(R.string.homepod_off))
                binding.songInProgress = false
            }
        }

        viewModel.homepodStateSaved.observe(viewLifecycleOwner) { changedSuccessfully ->
            if (!changedSuccessfully) {
                binding.showSnackbar(getString(R.string.issue_occured))
            }
        }
    }

    override fun stopOps() {}

    /*
     * Navigates to the homepod screen via safeargs
     */

    private fun goToMusicScreen() {
        val action = HomepodFragmentDirections.goToMusicScreen()
        findNavController().navigate(action)
    }
}