package com.example.notecloud.navigation.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notecloud.R
import com.example.notecloud.databinding.FragmentHomeBinding
import com.example.notecloud.db.entity.ModelCloud
import com.example.notecloud.navigation.listener.ItemListenerCloud
import com.example.notecloud.viewmodel.ViewModelCloud
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlin.system.exitProcess

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener, ItemListenerCloud {

    private lateinit var disposable: CompositeDisposable
    private lateinit var binding: FragmentHomeBinding
    private val viewModelCloud: ViewModelCloud by activityViewModels()
    private val args: HomeFragmentArgs by navArgs()
    private lateinit var adapterCloud: AdapterCloud
    lateinit var modelCloud: ModelCloud
    private var model = mutableListOf<ModelCloud>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNote.setOnClickListener(this)
        pressedCallback()
        adapterCloud = AdapterCloud(
            model,
                this@HomeFragment, onClickDelete =  {position -> viewModelCloud.deleteCloud(position)})
        viewRc()
    }


    private fun viewRc() {
        binding.apply {
            rcCloud.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rcCloud.adapter = adapterCloud
        }
        viewModelCloud.getAllCloud.observe(requireActivity(), Observer { note ->
            adapterCloud.getData(note)
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.addNote -> {
                val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
                findNavController().navigate(action)
            }
            R.id.delete_button -> {

            }
        }
    }

    override fun onCloudClick(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToAddFragment(false, id)
        findNavController().navigate(action)
    }

    private fun pressedCallback() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(requireContext())
                    .setMessage("¿Salir de la aplicación?")
                    .setCancelable(false)
                    .setPositiveButton("Si") { _, _ ->
                        exitProcess(0)
                    }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}