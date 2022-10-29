package com.stathis.smartassistant.ui.rooms.music

import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.databinding.FragmentMusicBinding
import com.stathis.smartassistant.util.setScreenTitle


class MusicFragment : BaseFragment<FragmentMusicBinding>(R.layout.fragment_music) {

    private val viewModel: MusicViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.music))
    }

    override fun startOps() {}

    override fun stopOps() {}
}