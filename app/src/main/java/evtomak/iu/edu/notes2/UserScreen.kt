package evtomak.iu.edu.notes2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// UserScreen: Fragment for user authentication including sign in, sign up, and sign out.
class UserScreen : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userRepository: UserRepository

    // onCreateView: Inflates the layout and initializes UI components.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("UserScreen", "onCreateView called")
        val view = inflater.inflate(R.layout.fragment_user_screen, container, false)
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)

        // Initialize UserRepository using the Singleton
        userRepository = UserRepositorySingleton.getInstance()

        // Initialize ViewModel
        val factory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        return view
    }

    // onViewCreated: Sets up UI behavior and event listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("UserScreen", "onViewCreated called")

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        view.findViewById<Button>(R.id.signInButton).setOnClickListener { onSignInClicked(it) }
        view.findViewById<Button>(R.id.signUpButton).setOnClickListener { onSignUpClicked(it) }
        view.findViewById<Button>(R.id.signOutButton).setOnClickListener { onSignOutClicked(it) }
    }

    // onDestroyView: Removes observers when the view is destroyed.
    override fun onDestroyView() {
        super.onDestroyView()
        userViewModel.user.removeObservers(viewLifecycleOwner)
    }

    // onSignInClicked: Handles sign in button click.
    private fun onSignInClicked(view: View) {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Email and password required.", Toast.LENGTH_SHORT).show()
            return
        }

        userViewModel.login(email, password)

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                // Always navigate to the NotesListFragment when the user is signed in
                findNavController().navigate(R.id.action_userScreen_to_notesListFragment)
            }
            else {
                // User is not signed in
                Toast.makeText(context, "Sign in failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // onSignUpClicked: Handles sign up button click.
    private fun onSignUpClicked(view: View) {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Email and password required.", Toast.LENGTH_SHORT).show()
            return
        }

        userViewModel.register(email, password)

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                // Navigate to the NotesListFragment when the user is registered successfully
                findNavController().navigate(R.id.action_userScreen_to_notesListFragment)
                Toast.makeText(context, "Registration successful.", Toast.LENGTH_SHORT).show()
            }
            else {
                // User registration failed
                Toast.makeText(context, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // onSignOutClicked: Handles sign out button click.
    private fun onSignOutClicked(view: View) {
        userViewModel.logout()
        Toast.makeText(context, "Signed out successfully.", Toast.LENGTH_SHORT).show()
    }
}
