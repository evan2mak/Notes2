package evtomak.iu.edu.notes2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class NoteScreen : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var noteEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: ImageButton
    private val noteViewModel: NoteViewModel by viewModels { NoteViewModelFactory(NoteRepositorySingleton.getInstance()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_screen)  // Replace with your actual layout resource ID

        titleEditText = findViewById(R.id.titleEditText)  // Replace with your actual view ID
        noteEditText = findViewById(R.id.noteEditText)  // Replace with your actual view ID
        saveButton = findViewById(R.id.saveButton)  // Replace with your actual view ID
        deleteButton = findViewById(R.id.deleteNoteButton)  // Replace with your actual view ID

        val noteId = intent.getStringExtra("noteId")
        if (noteId != null) {
            loadNoteDetails(noteId)
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = noteEditText.text.toString()
            if (title.isNotBlank() && content.isNotBlank()) {
                val note = Note(UUID.randomUUID().toString(), title, content)
                noteViewModel.addNote(note)
                finish()
            }
            else {
                Toast.makeText(this, "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            deleteNote()
        }
    }

    private fun loadNoteDetails(noteId: String) {
        val note = noteViewModel.notes.value?.find { it.id == noteId }
        if (note != null) {
            titleEditText.setText(note.title)
            noteEditText.setText(note.content)
        } else {
            Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun deleteNote() {
        val noteId = intent.getStringExtra("NOTE_ID") ?: return
        val note = noteViewModel.notes.value?.find { it.id == noteId }
        if (note != null) {
            noteViewModel.deleteNote(note)
            finish()
        }
        else {
            Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show()
        }
    }
}
