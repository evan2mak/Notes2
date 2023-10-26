package evtomak.iu.edu.notes2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class UserScreen : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize UserRepository
        val userRepository = UserRepository()

        // Initialize ViewModel
        val factory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                // User is logged in, navigate to notes list
                findNavController().navigate(R.id.action_userScreen_to_notesListFragment)
            }
        }

        // TODO: Implement user authentication UI and logic

        return inflater.inflate(R.layout.fragment_user_screen, container, false)
    }
}
