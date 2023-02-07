package com.example.android2_lessen2.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.android2_lessen2.R
import com.example.android2_lessen2.databinding.FragmentOnBoardBinding
import com.example.android2_lessen2.ui.adapters.OnBoardViewPagerAdapter
import com.example.android2_lessen2.utils.PreferenceHelper

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() = with(binding) {
        viewPager2.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        dotsIndicator.attachTo(viewPager2)
        PreferenceHelper.unit(requireContext())
        PreferenceHelper.safeBool = true
    }

    private fun setupListener() = with(binding.viewPager2) {
        binding.tvProp.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }

        binding.btnK.setOnClickListener {
            //App.preferenceHelper.safeBool = true
            findNavController().navigate(R.id.registrationFragment)
        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.tvProp.isVisible = true
                        binding.btnK.isVisible = false
                    }
                    1 -> {
                        binding.tvProp.isVisible = true
                        binding.btnK.isVisible = false
                    }
                    2 -> {
                        binding.tvProp.isVisible = false
                        binding.btnK.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
}