package com.stathis.smartassistant.ui.dashboard.home

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.smartassistant.R
import com.stathis.smartassistant.abstraction.BaseFragment
import com.stathis.smartassistant.callbacks.HomeScreenCallback
import com.stathis.smartassistant.databinding.FragmentRoomsBinding
import com.stathis.smartassistant.models.RoomPromo
import com.stathis.smartassistant.models.SmartLockerPromo
import com.stathis.smartassistant.ui.rooms.RoomsActivity
import com.stathis.smartassistant.ui.wardrobe.WardrobeActivity
import com.stathis.smartassistant.util.*


class HomeFragment : BaseFragment<FragmentRoomsBinding>(R.layout.fragment_rooms) {

    private val viewModel: HomeViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.home_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        binding.userGreetingTxtView.setUserGreeting()
        binding.currentDateTxtView.text = getCurrentFullDate()

        viewModel.getData(object : HomeScreenCallback {
            override fun onRoomsPromoClick(promo: RoomPromo) {
                binding.showSnackbar("Τα δωμάτια δεν έχουν υλοποιηθεί ακόμα")
            }

            override fun onSmartLockerClick(promo: SmartLockerPromo) {
                goToSmartWardrobeScreen()
            }
        })

        viewModel.userImg.observe(viewLifecycleOwner) { imgUrl ->
            binding.userImgView.loadImage(imgUrl)
        }
    }

    override fun stopOps() {}

    //FIXME: Move intent to Rooms Activity that will be added soon

//    private fun goToRoomUtilsScreen(roomName: String) {
//        val action = Intent(requireContext(), RoomsActivity::class.java).apply {
//            putExtra(ROOM_NAME, roomName)
//        }
//        startActivity(action)
//    }

    /*
     * Starts the Room Activity in the flow.
     */

    private fun goToRoomsScreen() {
        val intent = Intent(requireContext(), RoomsActivity::class.java)
        startActivity(intent)
    }

    /*
     * Starts the SmartWardrobe activity in the flow
     */

    private fun goToSmartWardrobeScreen() {
        val intent = Intent(requireContext(), WardrobeActivity::class.java)
        startActivity(intent)
    }
}