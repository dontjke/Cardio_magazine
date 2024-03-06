package com.stepanov.cardiomagazine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.stepanov.cardiomagazine.databinding.FragmentCardioBinding
import com.stepanov.cardiomagazine.domain.CardioNote
import com.stepanov.cardiomagazine.domain.CardioNote.Companion.date
import com.stepanov.cardiomagazine.ui.adapter.CardioRecyclerViewAdapter
import com.stepanov.cardiomagazine.ui.adapter.ItemTouchHelperCallback
import com.stepanov.cardiomagazine.utils.addDate
import com.stepanov.cardiomagazine.viewmodel.CardioViewModel
import java.util.Calendar


class CardioFragment : Fragment(), AddClickListener {

    private var _binding: FragmentCardioBinding? = null
    private val binding: FragmentCardioBinding
        get() {
            return _binding!!
        }
    private val adapter = CardioRecyclerViewAdapter()

    private var itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
    private val cardioViewModel: CardioViewModel by lazy {
        ViewModelProvider(this)[CardioViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardioViewModel.loadData {
            adapter.setData(it.toMutableList())
        }



        itemTouchHelper.attachToRecyclerView(binding.cardioRecyclerView)
        binding.cardioRecyclerView.also {
            it.adapter = adapter
            it.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        dialogFragment()
    }


    private fun dialogFragment() {

        binding.heartFloatingActionButton.setOnClickListener {
            val dialogFragment = DialogFragment()
//            dialogFragment.setWidthPercent(85)
            dialogFragment.setOnClickListener(this)
            dialogFragment.show(
                childFragmentManager, DialogFragment.TAG
            )
        }
    }


    companion object {
        fun newInstance() = CardioFragment()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAddClick(cardioNote: CardioNote) {

        if (cardioViewModel.getData().contains(
                addDate()
            )
        ) {
            adapter.appendItem(cardioNote)
            cardioViewModel.addCardioNote(cardioNote)
        } else {
            adapter.appendItem(
                addDate()
            )
            cardioViewModel.addCardioNote(
               addDate()
            )
            adapter.appendItem(cardioNote)
            cardioViewModel.addCardioNote(cardioNote)
        }
    }
}
