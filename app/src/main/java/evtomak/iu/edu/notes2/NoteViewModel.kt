package evtomak.iu.edu.notes2

import androidx.lifecycle.ViewModel

// NoteViewModel: ViewModel for managing and performing operations on notes.
class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val notes = noteRepository.notes

    // addNote: Adds a new note to the repository.
    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    // updateNote: Updates an existing note in the repository.
    fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    // deleteNote: Deletes a note from the repository.
    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }
}
