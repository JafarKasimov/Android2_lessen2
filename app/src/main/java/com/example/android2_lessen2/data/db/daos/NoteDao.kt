package com.example.android2_lessen2.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android2_lessen2.models.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)

    @Delete
    fun delete(model: NoteModel)
}