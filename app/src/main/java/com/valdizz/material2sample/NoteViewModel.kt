package com.valdizz.material2sample

import android.app.Application
import androidx.lifecycle.*
import com.valdizz.material2sample.common.SortType
import com.valdizz.material2sample.db.Note
import com.valdizz.material2sample.db.NoteDb
import com.valdizz.material2sample.db.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    private var sortType = MutableLiveData<SortType>()
    var allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteDb.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = Transformations.switchMap(sortType) {
            when (it) {
                SortType.DEFAULT -> { repository.allNotes }
                SortType.NAME -> { repository.allNotesByName}
                SortType.DATE -> { repository.allNotesByDate }
                else -> { repository.allNotes }
            }
        }
    }

    fun getNotes(sortType: SortType) {
        this.sortType.value = sortType
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}