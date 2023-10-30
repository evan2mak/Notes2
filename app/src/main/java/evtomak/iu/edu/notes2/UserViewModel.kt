package evtomak.iu.edu.notes2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// UserViewModel: ViewModel for managing user authentication and registration.
class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val user = userRepository.user
    val navigateToNotesList = MutableLiveData<Boolean>()

    // login: Attempts to log in the user with the provided email and password.
    fun login(email: String, password: String) {
        userRepository.login(email, password) { success ->
            if (success) {
                navigateToNotesList.postValue(true)
            }
        }
    }
    // register: Attempts to register a new user with the provided email and password.
    fun register(email: String, password: String) {
        userRepository.register(email, password) { success ->
            if (success) {
                navigateToNotesList.postValue(true)
            }
        }
    }

    // logout: Logs out the current authenticated user.
    fun logout() {
        userRepository.logout()
    }
}
