package com.example.android2_lessen2.interfaci

import com.example.android2_lessen2.models.NoteModel

interface OnClick {
    fun onLongClick(noteModel: NoteModel)
}