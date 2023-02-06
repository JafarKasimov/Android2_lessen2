package com.example.android2_lessen2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android2_lessen2.R
import com.example.android2_lessen2.databinding.FragmentOnBoardPegingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPegingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPegingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ON_BOARD_PAGE_POSITION)) {
            0 -> {
                tvText.text = "Очень удобный функционал"
                lottie.setAnimation(R.raw.lottie1)
            }
            1 -> {
                tvText.text = "Быстрый качественный продукт"
                lottie.setAnimation(R.raw.lottie2)
            }
            2 -> {
                tvText.text = "Куча функций и интересных фишек"
                lottie.setAnimation(R.raw.lottie3)
            }
        }
    }

    companion object {
        const val ARG_ON_BOARD_PAGE_POSITION = "onBoardPager"
    }
}