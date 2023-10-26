package evtomak.iu.edu.notes2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class UserRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        firebaseAuth.currentUser?.let {
            _user.value = User(it.uid, it.email!!)
        }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.let {
                        _user.value = User(it.uid, it.email!!)
                    }
                } else {
                    // Handle login failure
                }
            }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.let {
                        _user.value = User(it.uid, it.email!!)
                    }
                } else {
                    // Handle registration failure
                }
            }
    }

    fun logout() {
        firebaseAuth.signOut()
        _user.value = null
    }
}
