package com.stepanov.cardiomagazine.repository

import com.stepanov.cardiomagazine.domain.CardioNote
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FakeRepository {
    private val time = SimpleDateFormat("HH:mm", Locale("Ru"))

    val listOfCardioNotes = mutableListOf(
        CardioNote(
            CardioNote.TYPE_CARDIO,
            time.format(Calendar.getInstance().time),
            123,
            321,
            33
        ),
        CardioNote(
            CardioNote.TYPE_CARDIO,
            time.format(Calendar.getInstance().time),
            124,
            322,
            35
        ),
        CardioNote(
            CardioNote.TYPE_CARDIO,
            time.format(Calendar.getInstance().time),
            125,
            323,
            36
        ),
        CardioNote(
            CardioNote.TYPE_CARDIO,
            time.format(Calendar.getInstance().time),
            126,
            324,
            37
        )
    )


}