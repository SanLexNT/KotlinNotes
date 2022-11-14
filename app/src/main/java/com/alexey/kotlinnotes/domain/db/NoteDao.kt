package com.alexey.kotlinnotes.domain.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alexey.kotlinnotes.domain.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: Note)

    @Delete
    fun removeNote(note:Note)

    @Query("SELECT * FROM notes")
    fun getNotes() : LiveData<List<Note>>

    @Update
    fun editNote(note: Note)
}