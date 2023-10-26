package evtomak.iu.edu.notes2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NoteRepository {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    // TODO: Implement note management logic here

    fun addNote(note: Note) {
        // TODO: Implement logic to add a note
        // Update _notes LiveData
    }

    fun updateNote(note: Note) {
        // TODO: Implement logic to update a note
        // Update _notes LiveData
    }

    fun deleteNote(note: Note) {
        // TODO: Implement logic to delete a note
        // Update _notes LiveData
    }
}
