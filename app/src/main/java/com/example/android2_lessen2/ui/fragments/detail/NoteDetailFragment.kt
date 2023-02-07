package com.example.android2_lessen2.ui.fragments.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android2_lessen2.App
import com.example.android2_lessen2.R
import com.example.android2_lessen2.databinding.FragmentNoteDetailBinding
import com.example.android2_lessen2.models.NoteModel

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var backgroundColor = "#1B1A1A"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
        fragmentUp()
    }

    private fun fragmentUp() {
        binding.arrowLeft.setOnClickListener {
            findNavController().navigate(R.id.action_noteDetailFragment_to_noteAppFragment2)
        }
        binding.btn1.setOnClickListener {
            backgroundColor = "#1E1E1E"
            binding.miniB1.isVisible = true
            binding.miniB2.isVisible = false
            binding.miniB3.isVisible = false
        }
        binding.btn2.setOnClickListener {
            backgroundColor = "#EBE4C9"
            binding.miniB1.isVisible = false
            binding.miniB2.isVisible = true
            binding.miniB3.isVisible = false
        }
        binding.btn3.setOnClickListener {
            backgroundColor = "#571818"
            binding.miniB1.isVisible = false
            binding.miniB2.isVisible = false
            binding.miniB3.isVisible = true
        }
    }

    private fun sendData() = with(binding) {
        enter.setOnClickListener {
            if (etTex1.text.isNotEmpty() || etTex2.text.isNotEmpty()) {
                val title = etTex1.text.toString()
                val direction = etTex2.text.toString()
                val data = tvMay.text.toString()

                App().getInstance()?.noteDao()?.insert(NoteModel(title,direction, color = backgroundColor,data))
                findNavController().navigateUp()
            }
        }
    }
}