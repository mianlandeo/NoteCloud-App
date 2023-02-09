package com.example.notecloud.navigation.ui.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.notecloud.R
import com.example.notecloud.databinding.PriorityDialogBinding
import com.example.notecloud.navigation.listener.PriorityDialogListener

class PriorityDialog(private val priorityListener: PriorityDialogListener): DialogFragment(),
    View.OnClickListener,
    RadioGroup.OnCheckedChangeListener{

    private lateinit var binding: PriorityDialogBinding
    private var priority : Int = -1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        binding = PriorityDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.70).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSavePriority.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.radioGroup.setOnCheckedChangeListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSavePriority -> {
                priorityListener.onSave(priority)
                dialog?.dismiss()
            }
            R.id.btnCancel ->{
                dialog?.dismiss()
            }
        }
    }

    override fun onCheckedChanged(radio: RadioGroup?, idRadio: Int) {
        binding.apply {
            when (idRadio){
                rbHigh.id -> {
                    priority = 3
                }
                rbMedium.id -> {
                    priority = 2
                }
                rbNormal.id -> {
                    priority = 1
                }
            }
        }
    }
}