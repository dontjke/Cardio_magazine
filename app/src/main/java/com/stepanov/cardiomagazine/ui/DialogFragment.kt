package com.stepanov.cardiomagazine.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stepanov.cardiomagazine.databinding.FragmentDialogBinding
import com.stepanov.cardiomagazine.domain.CardioNote
import com.stepanov.cardiomagazine.domain.CardioNote.Companion.time
import java.util.Calendar

class DialogFragment : androidx.fragment.app.DialogFragment() {
    private var _binding: FragmentDialogBinding? = null
    private val binding: FragmentDialogBinding
        get() {
            return _binding!!
        }
    private lateinit var listener: AddClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val systolicPressureValue = binding.systolicInputEditText.text
        val diastolicPressureValue = binding.diastolicInputEditText.text
        val pulseValue = binding.pulseInputEditText.text
        binding.addButton.setOnClickListener {
            listener.onAddClick(
                CardioNote(
                    time = time.format(Calendar.getInstance().time),
                    systolicPressure = systolicPressureValue.toString().toInt(),
                    diastolicPressure = diastolicPressureValue.toString()
                        .toInt(),
                    pulse = pulseValue.toString().toInt(),
                    type = CardioNote.TYPE_CARDIO
                )
            )

            dialog?.dismiss()

        }
    }

    companion object {
        const val TAG = "DialogFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setOnClickListener(listener: AddClickListener) {
        this.listener = listener
    }
}
