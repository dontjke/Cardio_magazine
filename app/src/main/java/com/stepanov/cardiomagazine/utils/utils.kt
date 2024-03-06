package com.stepanov.cardiomagazine.utils

import com.stepanov.cardiomagazine.domain.CardioNote
import java.util.Calendar

fun addDate() =
    CardioNote(
        type = CardioNote.TYPE_HEADER,
        time = CardioNote.date.format(Calendar.getInstance().time),
        systolicPressure = 0,
        diastolicPressure = 0,
        pulse = 0
    )
