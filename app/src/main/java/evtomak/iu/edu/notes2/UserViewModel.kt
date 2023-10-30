package evtomak.iu.edu.notes2

import androidx.lifecycle.ViewModel

// UserViewModel: ViewModel for managing user authentication and registration.
class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val user = userRepository.user

    // login: Attempts to log in the user with the provided email and password.
    fun login(email: String, password: String) {
        userRepository.login(email, password)
    }

    // register: Attempts to register a new user with the provided email and password.
    fun register(email: String, password: String) {
        userRepository.register(email, password)
    }

    // logout: Logs out the current authenticated user.
    fun logout() {
        userRepository.logout()
    }
}
