package evtomak.iu.edu.notes2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import android.util.Log

class NoteRepository {
    private val database = FirebaseDatabase.getInstance()
    private val notesRef = database.reference.child("notes")
    private val _notes = MutableLiveData<List<Note>>(emptyList())
    val notes: LiveData<List<Note>> get() = _notes

    init {
        notesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val notes = mutableListOf<Note>()
                for (noteSnapshot in snapshot.children) {
                    val note = noteSnapshot.getValue(Note::class.java)
                    note?.let { notes.add(it) }
                }
                _notes.value = notes
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("NoteRepository", "Failed to read notes", error.toException())
            }
        })
    }

    fun addNote(note: Note) {
        notesRef.child(note.id).setValue(note)
    }

    fun updateNote(note: Note) {
        notesRef.child(note.id).setValue(note)
    }

    fun deleteNote(note: Note) {
        notesRef.child(note.id).removeValue()
    }
}
