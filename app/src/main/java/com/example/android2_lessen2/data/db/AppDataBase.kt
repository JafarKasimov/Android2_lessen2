package com.example.android2_lessen2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android2_lessen2.data.db.daos.NoteDao
import com.example.android2_lessen2.models.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}