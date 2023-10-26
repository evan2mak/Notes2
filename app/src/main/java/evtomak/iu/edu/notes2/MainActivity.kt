package evtomak.iu.edu.notes2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.notesRecyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        notesAdapter = NotesAdapter()
        recyclerView.adapter = notesAdapter

        // Check if user is logged in
        if (auth.currentUser == null) {
            navigateToUserScreen()
        } else {
            showNotes()
        }
    }

    private fun navigateToUserScreen() {
        // TODO: Implement navigation to UserScreen Fragment
    }

    private fun showNotes() {
        // TODO: Implement logic to show notes
    }
}
