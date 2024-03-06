package com.stepanov.cardiomagazine.domain

data class RemoteCardioNote(
    val type: Int = 0,
    val time: String = "",
    val systolicPressure: Int = 0,
    val diastolicPressure: Int = 0,
    val pulse: Int = 0,
)
