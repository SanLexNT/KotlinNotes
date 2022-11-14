package com.alexey.kotlinnotes.domain.db

import androidx.lifecycle.LiveData
import com.alexey.kotlinnotes.domain.Note

interface NoteRepository {

    val notes: LiveData<List<Note>>

    fun insertNote(note: Note)
    fun removeNote(note: Note)
    fun editNote(note: Note)
}