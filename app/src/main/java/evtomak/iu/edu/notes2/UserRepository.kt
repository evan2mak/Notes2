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
                    Log.d("FirebaseAuth", "Login successful")
                }
                else {
                    Log.e("FirebaseAuth", "Login failed", task.exception)
                    _user.value = null
                }
            }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.let {
                        val user = User(it.uid, it.email!!)
                        _user.value = user
                        saveUserToDatabase(user)
                    }
                    Log.d("FirebaseAuth", "Registration successful")
                }
                else {
                    Log.e("FirebaseAuth", "Registration failed", task.exception)
                    _user.value = null
                }
            }
    }


    private fun saveUserToDatabase(user: User) {
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(user.id).setValue(user)
    }


    fun logout() {
        firebaseAuth.signOut()
        _user.value = null
    }
}
