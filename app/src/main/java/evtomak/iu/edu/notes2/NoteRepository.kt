package evtomak.iu.edu.notes2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import android.util.Log

// NoteRepository: Manages data operations for notes, including retrieval, addition, update, and deletion.
class NoteRepository {
    private val database = FirebaseDatabase.getInstance() // Instance of Firebase Database.
    private val notesRef = database.reference.child("notes") // Reference to the 'notes' node in Firebase Database.
    private val _notes = MutableLiveData<List<Note>>(emptyList()) // Private mutable live data holding the list of notes.
    val notes: LiveData<List<Note>> get() = _notes // Public live data for observing the list of notes.

    // init: Initializes a ValueEventListener to update the list of notes upon any changes in the Firebase Database.
    init {
        notesRef.addValueEventListener(object : ValueEventListener {
            // onDataChange: Updates the list of notes when data in Firebase Database changes.
            override fun onDataChange(snapshot: DataSnapshot) {
                val notes = mutableListOf<Note>()
                for (noteSnapshot in snapshot.children) {
                    val note = noteSnapshot.getValue(Note::class.java)
                    note?.let { notes.add(it) }
                }
                _notes.value = notes
            }

            // onCancelled: Logs an error if reading from Firebase Database is cancelled.
            override fun onCancelled(error: DatabaseError) {
                Log.e("NoteRepository", "Failed to read notes", error.toException())
            }
        })
    }

    // addNote: Adds a new note to the Firebase Database.
    fun addNote(note: Note) {
        notesRef.child(note.id).setValue(note)
    }

    // updateNote: Updates an existing note in the Firebase Database.
    fun updateNote(note: Note) {
        notesRef.child(note.id).setValue(note)
    }

    // deleteNote: Deletes a note from the Firebase Database.
    fun deleteNote(note: Note) {
        notesRef.child(note.id).removeValue()
    }
}

