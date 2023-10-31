package evtomak.iu.edu.notes2

// Data class for notes
data class Note(
    val id: String = "",
    val userId: String = "", // Add a field for user UID
    val title: String = "",
    val content: String = ""
)

