package com.example.android2_lessen2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(
    val title: String,
    val description: String,
    val color: String,
    val data: String
    ):java.io.Serializable{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}