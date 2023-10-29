package evtomak.iu.edu.notes2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        // Check if a user is already authenticated
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // If a user is authenticated, set the user LiveData
            _user.value = User(currentUser.uid, currentUser.email!!)
        }
        else {
            // If no user is authenticated, set the user LiveData to null
            _user.value = null
        }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    if (currentUser != null) {
                        // If login is successful, set the user LiveData
                        _user.value = User(currentUser.uid, currentUser.email!!)
                        Log.d("FirebaseAuth", "Login successful")
                    }
                } else {
                    Log.e("FirebaseAuth", "Login failed", task.exception)
                    _user.value = null // Set to null if login fails
                }
            }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    if (currentUser != null) {
                        val user = User(currentUser.uid, currentUser.email!!)
                        _user.value = user
                        saveUserToDatabase(user)
                        Log.d("FirebaseAuth", "Registration successful")
                    }
                } else {
                    Log.e("FirebaseAuth", "Registration failed", task.exception)
                    _user.value = null // Set to null if registration fails
                }
            }
    }

    private fun saveUserToDatabase(user: User) {
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(user.id).setValue(user)
    }

    fun logout() {
        firebaseAuth.signOut()
        _user.value = null // Set to null when the user logs out
    }
}
