package com.alexey.kotlinnotes.domain.db

import androidx.lifecycle.LiveData
import com.alexey.kotlinnotes.domain.Note

class NoteRealization(private val noteDao: NoteDao) : NoteRepository {

    override val notes: LiveData<List<Note>>
        get() = noteDao.getNotes()


    override fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override fun removeNote(note: Note) {
        noteDao.removeNote(note)
    }

    override fun editNote(note: Note) {
        noteDao.editNote(note)
    }
}