package com.valdizz.material2sample.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    val allNotesByName: LiveData<List<Note>> = noteDao.getAllNotesByName()
    val allNotesByDate: LiveData<List<Note>> = noteDao.getAllNotesByDate()

    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    @WorkerThread
    suspend fun deleteAll() {
        noteDao.deleteAll()
    }
}