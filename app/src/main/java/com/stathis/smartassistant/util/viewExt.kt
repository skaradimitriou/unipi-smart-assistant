package com.stathis.smartassistant.util

import android.app.Activity
import android.text.format.DateFormat
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.stathis.smartassistant.R
import java.util.*

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

/**
 * Helper fun to show a simple date picker and get the result in dd/MM/yyyy date format.
 */

fun Fragment.showDatePicker(onDateSelected: (String) -> Unit) {
    val dateRangePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText(R.string.select_date)
        .build()

    dateRangePicker.show(requireActivity().supportFragmentManager, SELECT_DATE_TAG)
    dateRangePicker.addOnPositiveButtonClickListener { date ->
        val fullDate: String = DateFormat.format(DEFAULT_DATE_FORMAT, Date(date)).toString()
        onDateSelected.invoke(fullDate)
    }
}

fun Fragment.showTimePicker(onTimeSelected: (String) -> Unit) {
    val picker = MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_24H)
        .setHour(12)
        .setMinute(10)
        .build()

    picker.show(requireActivity().supportFragmentManager, SELECT_TIME_TAG)
    picker.addOnPositiveButtonClickListener {
        val minute = picker.minute
        val hour = picker.hour
        onTimeSelected.invoke("$hour:$minute")
    }
}