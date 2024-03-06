package com.stepanov.cardiomagazine.repository

import com.stepanov.cardiomagazine.domain.CardioNote

interface CardioNoteRepository {
    fun addCardioNote(cardioNote: CardioNote)
    fun getCardioNotes(listener: (List<CardioNote>) -> Unit)
}