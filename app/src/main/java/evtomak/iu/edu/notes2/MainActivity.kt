package evtomak.iu.edu.notes2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        auth = FirebaseAuth.getInstance()

        // Initialize the NavController
        navController = findNavController(R.id.nav_host_fragment)

        // Initialize UserRepository using the Singleton
        userRepository = UserRepositorySingleton.getInstance()

        // Check if user is logged in and navigate accordingly
        if (auth.currentUser == null) {
            navigateToUserScreen()
        }
        else {
            navigateToNotesList()
        }
    }

    private fun navigateToUserScreen() {
        navController.navigate(R.id.userScreen)
    }

    private fun navigateToNotesList() {
        navController.navigate(R.id.notesListFragment)
    }
}
