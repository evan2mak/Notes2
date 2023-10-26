package evtomak.iu.edu.notes2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

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

        return inflater.inflate(R.layout.fragment_user_screen, container, false)
    }

    // TODO: Implement user authentication logic
}

