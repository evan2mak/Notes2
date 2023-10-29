package evtomak.iu.edu.notes2

object NoteRepositorySingleton {
    private var instance: NoteRepository? = null

    fun getInstance(): NoteRepository {
        if (instance == null) {
            instance = NoteRepository()
        }
        return instance!!
    }
}
