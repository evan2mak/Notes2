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

class UserScreen : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private var hasNavigated = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("UserScreen", "onCreateView called")
        val view = inflater.inflate(R.layout.fragment_user_screen, container, false)
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)

        // Initialize UserRepository
        val userRepository = UserRepository()

        // Initialize ViewModel
        val factory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("UserScreen", "onViewCreated called")

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        view.findViewById<Button>(R.id.signInButton).setOnClickListener { onSignInClicked(it) }
        view.findViewById<Button>(R.id.signUpButton).setOnClickListener { onSignUpClicked(it) }
        view.findViewById<Button>(R.id.signOutButton).setOnClickListener { onSignOutClicked(it) }

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                if (!findNavController().previousBackStackEntry?.destination?.id?.equals(R.id.notesListFragment)!!) {
                    // Only navigate to NotesListFragment if coming from elsewhere
                    findNavController().navigate(R.id.action_userScreen_to_notesListFragment)
                }
            }
            else {
                // User is not signed in, reset hasNavigated to allow future navigation
                hasNavigated = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userViewModel.user.removeObservers(viewLifecycleOwner)
    }

    private fun onSignInClicked(view: View) {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        userViewModel.login(email, password)
    }

    private fun onSignUpClicked(view: View) {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        userViewModel.register(email, password)
    }

    private fun onSignOutClicked(view: View) {
        userViewModel.logout()
        Toast.makeText(context, "Signed out successfully.", Toast.LENGTH_SHORT).show()
    }
}
