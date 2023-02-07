package com.example.android2_lessen2.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2_lessen2.databinding.ItemNoteAppBinding
import com.example.android2_lessen2.interfaci.OnClick
import com.example.android2_lessen2.models.NoteModel

class NoteAppAdapter(
    val onItemClick: OnClick,
    private var list: List<NoteModel> = ArrayList()
) : RecyclerView.Adapter<NoteAppAdapter.NoteViewHolder>() {

    fun setList(list: List<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemNoteAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(noteModel: NoteModel) = with(binding) {

            titleTxt.text = noteModel.title
            descriptionTxt.text = noteModel.description
            textData.text = noteModel.data
            cardView.setCardBackgroundColor(Color.parseColor(noteModel.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteAppBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnLongClickListener {
            onItemClick.onLongClick(list[position])
            true
        }
    }

    override fun getItemCount(): Int = list.size
}
