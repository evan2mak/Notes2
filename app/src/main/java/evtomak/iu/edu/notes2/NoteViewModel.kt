package evtomak.iu.edu.notes2

import androidx.lifecycle.ViewModel

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val notes = noteRepository.notes

    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }
}
