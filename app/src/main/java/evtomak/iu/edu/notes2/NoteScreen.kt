package evtomak.iu.edu.notes2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class NoteScreen : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_screen)

        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)

        // Initialize NoteRepository
        val noteRepository = NoteRepository()

        // Initialize ViewModel
        val factory = NoteViewModelFactory(noteRepository)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        // TODO: Load the note if editing an existing one
    }

    // ... rest of your code

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                // TODO: Show confirmation dialog and delete the note
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
