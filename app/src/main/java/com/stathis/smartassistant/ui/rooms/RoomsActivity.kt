package com.stathis.smartassistant.ui.rooms

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseActivity
import com.stathis.smartassistant.databinding.ActivityRoomsBinding
import com.stathis.smartassistant.util.ROOM_NAME
import com.stathis.smartassistant.util.showAlertDialog

class RoomsActivity : BaseActivity<ActivityRoomsBinding>(R.layout.activity_rooms) {

    private val viewModel: RoomsViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel

        val selectedRoom = intent.getStringExtra(ROOM_NAME) ?: ""
        title = selectedRoom
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        viewModel.onRoomUtilTap { model ->
            //handle model tap
        }
    }

    override fun stopOps() {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.general_info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.infoItem -> {
            showAlertDialog(getString(R.string.rooms_info))
            true
        }
        else -> false
    }
}