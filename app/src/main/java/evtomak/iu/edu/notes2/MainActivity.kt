package evtomak.iu.edu.notes2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// MainActivity: Manages the main user interface and navigation based on user authentication status.
class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth // FirebaseAuth instance for managing user authentication.
    private lateinit var navController: NavController // NavController for managing app navigation.
    private lateinit var userRepository: UserRepository // UserRepository for managing user data.

    // onCreate: Initializes Firebase, NavController, UserRepository, and navigates to the appropriate screen based on user authentication.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase and enable offline data persistence.
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        auth = FirebaseAuth.getInstance()

        // Initialize the NavController for navigation.
        navController = findNavController(R.id.nav_host_fragment)

        // Initialize UserRepository using the Singleton pattern.
        userRepository = UserRepositorySingleton.getInstance()

        // Check user authentication status and navigate to the appropriate screen.
        if (auth.currentUser == null) {
            navigateToUserScreen()
        }
        else {
            navigateToNotesList()
        }
    }

    // navigateToUserScreen: Navigates to the user authentication or profile screen.
    private fun navigateToUserScreen() {
        navController.navigate(R.id.userScreen)
    }

    // navigateToNotesList: Navigates to the screen displaying the list of notes.
    private fun navigateToNotesList() {
        navController.navigate(R.id.notesListFragment)
    }
}

