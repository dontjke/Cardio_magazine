package com.stepanov.cardiomagazine.ui

import com.stepanov.cardiomagazine.domain.CardioNote

interface AddClickListener {
    fun onAddClick(cardioNote: CardioNote)
}