package evtomak.iu.edu.notes2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// NoteViewModelFactory: Factory class for creating instances of NoteViewModel.
class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.Factory {

    // create: Creates and returns an instance of NoteViewModel or throws an exception if the ViewModel class is unknown.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
