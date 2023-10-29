package evtomak.iu.edu.notes2

// UserRepositorySingleton.kt

object UserRepositorySingleton {
    private var instance: UserRepository? = null

    fun getInstance(): UserRepository {
        if (instance == null) {
            instance = UserRepository()
        }
        return instance!!
    }
}
