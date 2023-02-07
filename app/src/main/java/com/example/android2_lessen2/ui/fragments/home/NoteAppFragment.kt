package com.example.android2_lessen2.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android2_lessen2.App
import com.example.android2_lessen2.R
import com.example.android2_lessen2.databinding.FragmentNoteAppBinding
import com.example.android2_lessen2.interfaci.OnClick
import com.example.android2_lessen2.models.NoteModel
import com.example.android2_lessen2.ui.adapters.NoteAppAdapter

class NoteAppFragment : Fragment(), OnClick {

    private lateinit var binding: FragmentNoteAppBinding
    private val list = ArrayList<NoteModel>()
    private val noteAppAdapter = NoteAppAdapter(this,list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setList()
    }

    private fun setupListener() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteAppFragment_to_noteDetailFragment)
        }
        binding.imageView.setOnClickListener {
            App.preferenceHelper.safeBool = false
            binding.rec.layoutManager = GridLayoutManager(requireContext() ,2)
            binding.imageView.isVisible = false
            binding.im1.isVisible = true
        }
        binding.im1.setOnClickListener {
            App.preferenceHelper.safeBool = true
            binding.rec.layoutManager = LinearLayoutManager(requireContext())
            binding.imageView.isVisible = true
            binding.im1.isVisible = false
        }
    }

    private fun initialize() {
        binding.rec.apply {
           layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAppAdapter
        }
    }

        private fun setList() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { list ->
            noteAppAdapter.setList(list as ArrayList<NoteModel>)
        }
    }


    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Are you sure you want to delete?")
        builder.setPositiveButton(
            "Delete") { dialog, id ->
            App.appDataBase?.noteDao()?.delete(noteModel)
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, id ->
            dialog.cancel()
        }
        builder.show()    }
}