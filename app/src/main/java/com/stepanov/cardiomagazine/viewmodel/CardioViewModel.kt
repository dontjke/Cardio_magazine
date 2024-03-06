package com.stepanov.cardiomagazine.viewmodel

import androidx.lifecycle.ViewModel
import com.stepanov.cardiomagazine.domain.CardioNote
import com.stepanov.cardiomagazine.repository.DefaultCardioNoteRepository
import com.stepanov.cardiomagazine.repository.FakeRepository

class CardioViewModel : ViewModel() {
    private val fakeRepository = FakeRepository()
    private val defaultCardioNoteRepository = DefaultCardioNoteRepository()

//    fun getData() = fakeRepository.listOfCardioNotes

    fun addCardioNote(cardioNote: CardioNote){
        defaultCardioNoteRepository.addCardioNote(cardioNote)
    }

    fun loadData(listener: (List<CardioNote>) -> Unit) {
        defaultCardioNoteRepository.getCardioNotes(listener)
    }

    fun getData() = defaultCardioNoteRepository.getLocalCardioNotes()



}