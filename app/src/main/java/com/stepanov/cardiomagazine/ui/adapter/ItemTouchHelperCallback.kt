package com.stepanov.cardiomagazine.ui.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ItemTouchHelperCallback(
    private val adapter: CardioRecyclerViewAdapter
) :
    ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val cardioSwipeFlags = ItemTouchHelper.START //or ItemTouchHelper.END
        val headerSwipeFlags = 0
        val swipeFlags =
            if (viewHolder is CardioRecyclerViewAdapter.CardioHolder) cardioSwipeFlags else headerSwipeFlags
        return makeMovementFlags(
            0,
            swipeFlags
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        return true
    }


    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }
}