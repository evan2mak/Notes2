package evtomak.iu.edu.notes2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.google.firebase.auth.FirebaseAuth

// NoteScreen: Activity for creating and editing notes.
class NoteScreen : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var noteEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: ImageButton
    private val noteViewModel: NoteViewModel by viewModels { NoteViewModelFactory(NoteRepositorySingleton.getInstance()) }
    private lateinit var auth: FirebaseAuth
    private var existingNoteId: String? = null

    // onCreate: Initializes the activity, sets up UI elements and event listeners.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_screen)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        titleEditText = findViewById(R.id.titleEditText)
        noteEditText = findViewById(R.id.noteEditText)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteNoteButton)

        existingNoteId = intent.getStringExtra("noteId")
        if (existingNoteId != null) {
            loadNoteDetails(existingNoteId!!)
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = noteEditText.text.toString()
            if (title.isNotBlank() && content.isNotBlank()) {
                if (existingNoteId == null) {
                    // Creating a new note
                    val newNote = Note(UUID.randomUUID().toString(), auth.currentUser?.uid ?: "", title, content)
                    noteViewModel.addNote(newNote)
                }
                else {
                    // Updating an existing note
                    val updatedNote = Note(existingNoteId!!, auth.currentUser?.uid ?: "", title, content)
                    noteViewModel.updateNote(updatedNote)
                }
                finish()
            }
            else {
                Toast.makeText(this, "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            if (existingNoteId == null) {
                // If creating a new note, just finish the activity
                finish()
            }
            else {
                // If editing an existing note, show delete confirmation dialog
                showDeleteConfirmationDialog()
            }
        }

    }

    // loadNoteDetails: Loads the details of an existing note into the UI.
    private fun loadNoteDetails(noteId: String) {
        val note = noteViewModel.notes.value?.find { it.id == noteId }
        if (note != null) {
            titleEditText.setText(note.title)
            noteEditText.setText(note.content)
        }
        else {
            Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // deleteNote: Deletes the current note.
    private fun deleteNote() {
        existingNoteId?.let { noteId ->
            val note = noteViewModel.notes.value?.find { it.id == noteId }
            if (note != null) {
                noteViewModel.deleteNote(note)
            }
            finish()
        }
    }

    // showDeleteConfirmationDialog: Shows a dialog to confirm note deletion.
    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Delete") { dialog, which ->
                deleteNote()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
