package com.example.notecloud.navigation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.notecloud.R
import com.example.notecloud.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        val logoAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.top_anim)
        val textAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.top_anim)
        Handler(Looper.myLooper()!!).postDelayed({
            binding.imgView.startAnimation(logoAnim)
            binding.titleSplash.startAnimation(textAnim)
            binding.progressCircular.visibility = View.VISIBLE
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, DELAY_SPLASH)
        return binding.root
    }
    companion object {
        private const val DELAY_SPLASH = 3000L
    }
}