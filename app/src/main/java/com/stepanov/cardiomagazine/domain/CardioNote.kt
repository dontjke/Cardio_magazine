package com.stepanov.cardiomagazine.domain

import java.text.SimpleDateFormat
import java.util.Locale


data class CardioNote(
    val type: Int = TYPE_CARDIO,
    val time: String = "",
    val systolicPressure: Int = 0,
    val diastolicPressure: Int = 0,
    val pulse: Int = 0,
) {
    companion object {
        const val TYPE_CARDIO = 0
        const val TYPE_HEADER = 1
        val time = SimpleDateFormat("HH:mm", Locale("Ru"))
        val date = SimpleDateFormat("dd MMMM", Locale("Ru"))
    }
}
