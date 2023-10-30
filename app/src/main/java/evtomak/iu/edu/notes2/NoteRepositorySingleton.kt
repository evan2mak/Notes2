package evtomak.iu.edu.notes2

// NoteRepositorySingleton: Provides a singleton instance of NoteRepository.
object NoteRepositorySingleton {
    private var instance: NoteRepository? = null // Nullable instance of NoteRepository.

    // getInstance: Returns the singleton instance of NoteRepository, creating it if necessary.
    fun getInstance(): NoteRepository {
        if (instance == null) {
            instance = NoteRepository()
        }
        return instance!!
    }
}
