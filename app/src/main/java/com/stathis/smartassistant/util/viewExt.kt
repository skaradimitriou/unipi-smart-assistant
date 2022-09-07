package com.stathis.smartassistant.util

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.snackbar.Snackbar
import com.stathis.smartassistant.R

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}

fun Fragment.addMenuProvider(menuProvider: MenuProvider) {
    requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
}

fun Fragment.showAlertDialog(message: String) {
    AlertDialog.Builder(requireActivity())
        .setMessage(message)
        .setPositiveButton(R.string.ok) { dialog, id ->
            dialog.dismiss()
        }.create().show()
}

fun Activity.showAlertDialog(message: String) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(R.string.ok) { dialog, id ->
            dialog.dismiss()
        }.create().show()
}

fun ViewDataBinding.showSnackbar(message: String) {
    Snackbar.make(this.root, message, Snackbar.LENGTH_LONG).show()
}