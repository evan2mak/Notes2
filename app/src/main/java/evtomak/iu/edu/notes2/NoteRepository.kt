package evtomak.iu.edu.notes2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NoteRepository {
    private val _notes = MutableLiveData<List<Note>>(emptyList())
    val notes: LiveData<List<Note>> get() = _notes

    fun addNote(note: Note) {
        val updatedNotes = _notes.value?.toMutableList() ?: mutableListOf()
        updatedNotes.add(note)
        _notes.value = updatedNotes
    }

    fun updateNote(note: Note) {
        val updatedNotes = _notes.value?.toMutableList() ?: mutableListOf()
        val index = updatedNotes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            updatedNotes[index] = note
            _notes.value = updatedNotes
        }
    }

    fun deleteNote(note: Note) {
        val updatedNotes = _notes.value?.toMutableList() ?: mutableListOf()
        updatedNotes.remove(note)
        _notes.value = updatedNotes
    }
}
