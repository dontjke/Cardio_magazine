package com.stepanov.cardiomagazine.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.stepanov.cardiomagazine.domain.CardioNote

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(cardioNote: CardioNote)
}