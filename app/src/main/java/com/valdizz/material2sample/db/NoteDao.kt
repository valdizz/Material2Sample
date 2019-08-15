package com.valdizz.material2sample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY date")
    fun getAllNotesByDate(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY note")
    fun getAllNotesByName(): LiveData<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}