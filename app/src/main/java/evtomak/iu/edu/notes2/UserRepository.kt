package evtomak.iu.edu.notes2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    // TODO: Implement user authentication logic here

    fun login(email: String, password: String) {
        // TODO: Implement login logic
        // Update _user LiveData based on authentication result
    }

    fun register(email: String, password: String) {
        // TODO: Implement registration logic
        // Update _user LiveData based on registration result
    }

    fun logout() {
        // TODO: Implement logout logic
        // Clear _user LiveData
        _user.value = null
    }
}
