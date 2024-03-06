package com.stepanov.cardiomagazine.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stepanov.cardiomagazine.databinding.CardioNoteRecyclerItemBinding
import com.stepanov.cardiomagazine.databinding.HeaderRecyclerItemBinding
import com.stepanov.cardiomagazine.domain.CardioNote
import com.stepanov.cardiomagazine.domain.CardioNote.Companion.TYPE_CARDIO
import com.stepanov.cardiomagazine.domain.CardioNote.Companion.TYPE_HEADER

class CardioRecyclerViewAdapter(
    private var data: MutableList<CardioNote> = mutableListOf()
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataNew: MutableList<CardioNote>) {
        this.data = dataNew
        notifyDataSetChanged()
    }


    fun appendItem(cardioNote: CardioNote) {
        data.add(cardioNote)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val bindingCardioNote = CardioNoteRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val bindingHeaderDate = HeaderRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return when (viewType) {
            TYPE_CARDIO -> CardioHolder(
                bindingCardioNote.root
            )

            TYPE_HEADER -> HeaderDateHolder(
                bindingHeaderDate.root
            )

            else -> CardioHolder(
                bindingCardioNote.root
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }


    inner class HeaderDateHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(cardioNote: CardioNote) {
            HeaderRecyclerItemBinding.bind(itemView).apply {
                headerDateTextView.text = cardioNote.time
            }
        }

    }

    inner class CardioHolder(itemView: View) : BaseViewHolder(itemView), ItemTouchHelperViewHolder {
        override fun bind(cardioNote: CardioNote) {
            CardioNoteRecyclerItemBinding.bind(itemView).apply {
                timeTextView.text = cardioNote.time
                diastolicPressure.text = cardioNote.diastolicPressure.toString()
                systolicPressure.text = cardioNote.systolicPressure.toString()
                pulse.text = cardioNote.pulse.toString()
                heartImageView.setOnClickListener {
                    removeItem()
                }
            }
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(Color.WHITE)
        }

    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }


}