package evtomak.iu.edu.notes2

import androidx.lifecycle.ViewModel

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val user = userRepository.user

    fun login(email: String, password: String) {
        userRepository.login(email, password)
    }

    fun register(email: String, password: String) {
        userRepository.register(email, password)
    }

    fun logout() {
        userRepository.logout()
    }
}
