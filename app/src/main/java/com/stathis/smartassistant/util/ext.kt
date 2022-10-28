package com.stathis.smartassistant.util

import android.animation.Animator
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.stathis.smartassistant.R
import com.stathis.smartassistant.models.EnergyModel
import com.stathis.smartassistant.models.Notification
import com.stathis.smartassistant.models.NotificationType
import com.stathis.smartassistant.models.SugarType
import com.stathis.smartassistant.models.wardrobe.ShoeCategory
import java.util.*

/**
 * This file contains the extension functions that are used across the app
 */


/**
 * Helper fun to load an Image url into an imageview with glide.
 * The mechanism loads a local image as a fallback mechanism when url is null
 */

fun ImageView.loadImage(url: String? = null) {
    url?.let {
        Glide.with(this.context).load(url).into(this)
    } ?: kotlin.run {
        this.setImageResource(R.mipmap.smarty_logo)
    }
}

/**
 * Helper fun to get current time and construct appropriate user greeting in home screen
 */

fun TextView.setUserGreeting() {
    val greeting = when (getCurrentTime().toInt()) {
        in 4..11 -> "Καλημέρα"
        else -> "Καλησπέρα"
    }

    val fullText = SpannableStringBuilder()
        .append(greeting)
        .append(" ")
        .bold { append("Γιάννη") }

    text = fullText
}


fun TextInputEditText.afterTextChanged(input: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            input.invoke(editable.toString())
        }
    })

}

fun TextInputEditText.onTextChanged(input: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
            input.invoke(p0.toString())

        override fun afterTextChanged(editable: Editable?) {}
    })
}

fun getDateAndTime(currentDate: (String) -> Unit, currentTime: (String) -> Unit) {
    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)

    currentDate.invoke("$day/$month/$year")
    currentTime.invoke(String.format("%02d:%02d", hour, minute))
}

fun TextInputLayout.showErrorMessageIfEmpty(errorMessage: String) {
    error = if (this.editText?.text.toString().isNotEmpty()) {
        null
    } else {
        errorMessage
    }
}

/**
 * Helper fun to get colors from resources
 */

fun Context.getActualColor(color: Int): Int = ContextCompat.getColor(this, color)

fun SugarType.toUiText(): String = when (name) {
    SugarType.NO_SUGAR.name -> "Σκέτο"
    SugarType.REGULAR.name -> "Μέτριο"
    SugarType.SWEET.name -> "Γλυκό"
    else -> ""
}

/**
 * Helper funs to know when an animation has finished playing
 */

fun LottieAnimationView.onAnimationEnd(ended: () -> Unit) {
    this.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(p0: Animator?) {}
        override fun onAnimationEnd(p0: Animator?) {
            ended.invoke()
        }

        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationRepeat(p0: Animator?) {}
    })
}

/**
 * Helper fun to play a custom animation and stop
 */

fun LottieAnimationView.playOnceAndStop(animation: Int, onEnd: () -> Unit) {
    setAnimation(animation)
    repeatCount = 0
    playAnimation()
    onAnimationEnd {
        onEnd.invoke()
    }
}

/**
 * Helper fun to display UI text from ShoeCategory enum
 */

fun ShoeCategory.toUiText(): String = when (name) {
    ShoeCategory.EVERYDAY.name -> "Καθημερινά"
    ShoeCategory.SPORTS.name -> "Αθλητικά"
    ShoeCategory.SPECIAL.name -> "Επίσημα"
    else -> "Άλλο είδος"
}

/**
 * Helper fun to display change in light state according to enabled value
 */

fun ImageView.setLightState(enabled: Boolean) = if (enabled) {
    setImageResource(R.drawable.light_on)
} else {
    setImageResource(R.drawable.lights_off)
}

/**
 * Helper fun to display change in temperature state according to enabled value
 */

fun TextView.setTemperatureState(enabled: Boolean) = if (enabled) {
    text = context.getString(R.string.active)
} else {
    text = context.getString(R.string.not_active)
}

fun TextView.setTemperatureInCelcius(temperature: Int) {
    text = context.getString(R.string.temperature_celcius, temperature)
}

/**
 * Helper fun to create Smarty's energy chart
 */

fun BarChart.setupChart(list: List<BarEntry>) {
    val dataset = BarDataSet(list, "kWh")
    dataset.colors = listOf(R.color.navy_blue)
    dataset.valueTextColor = Color.BLACK
    dataset.setDrawValues(false)

    val barData = BarData(dataset)

    data = barData
    axisRight.setDrawLabels(false)
    legend.isEnabled = false
    animateY(1000)
    description.isEnabled = false
    axisRight.setDrawGridLines(false)
    axisLeft.setDrawGridLines(false)
    xAxis.setDrawGridLines(false)
    setPinchZoom(false)
}

/**
 * Helper fun to add custom labels to the Smarty's chart.
 * Takes only the first 3 chars of each provided string
 */

fun BarChart.toChartLabels(data: List<EnergyModel>) {
    val labels = data.map { it.month.take(3) }
    xAxis.valueFormatter = IndexAxisValueFormatter(labels)
}

/**
 * Helper fun to create a single notification in a simple way
 */

fun createNotification(title: String, description: String, category : NotificationType): Notification = Notification(
    title = title,
    description = description,
    category = category,
    date = Date(),
    hasBeenRead = false
)