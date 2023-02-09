package com.example.notecloud.navigation.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notecloud.R
import com.example.notecloud.databinding.FragmentAddBinding
import com.example.notecloud.db.entity.ModelCloud
import com.example.notecloud.navigation.listener.PriorityDialogListener
import com.example.notecloud.navigation.ui.dialog.PriorityDialog
import com.example.notecloud.utils.priorityView
import com.example.notecloud.utils.snackBar
import com.example.notecloud.viewmodel.ViewModelCloud
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

import java.util.Date

@AndroidEntryPoint
class AddFragment : Fragment(), View.OnClickListener, PriorityDialogListener {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private var addPriority: Int = -1
    private var model: Int = 0
    private val formatter = SimpleDateFormat("dd MMM yyyy")
    private val args: AddFragmentArgs by navArgs()
    private val viewModelCloud: ViewModelCloud by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnBack -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            R.id.btnSave -> {
                val dialog = PriorityDialog(this)
                dialog.show(requireActivity().supportFragmentManager, "PriorityDialog")
            }
        }
    }

    private fun updateData() {
        val id = args.itemId
        if (id > 0) {
            viewModelCloud.getCloud(id).observe(this.viewLifecycleOwner) {
                model = it.id
                addPriority = it.priorityCloud
                binding.etTitle.setText(it.cloudTitle)
                binding.descriptionEdit.setText(it.cloudDescription)
                binding.cvPriority.setCardBackgroundColor(
                    requireContext().priorityView(
                        addPriority
                    )
                )
            }
        }
    }

    override fun onSave(priority: Int) {
        val noteTitle = binding.etTitle.text.toString().trim()
        val noteDescription = binding.descriptionEdit.text.toString().trim()
        if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
            val cloud = ModelCloud(
                id = model,
                cloudTitle = noteTitle,
                cloudDescription = noteDescription,
                priorityCloud = priority,
                dateCloud = formatter.format(Date()),
                upDateCloud = null
            )
            viewModelCloud.insertCloud(cloud)
            viewModelCloud.updateCloud(cloud)
            priorityChanger(priority)
            view?.snackBar("SAVE SUCCESS")
            findNavController().navigate(R.id.homeFragment)
        } else {
            view?.snackBar("Add Data")
        }
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun priorityChanger(priority: Int) {
        binding.cvPriority.setCardBackgroundColor(requireContext().priorityView(priority))
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}


