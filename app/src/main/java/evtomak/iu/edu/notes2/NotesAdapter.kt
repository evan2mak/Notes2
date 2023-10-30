package evtomak.iu.edu.notes2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

// NotesAdapter: Adapter for displaying a list of notes in a RecyclerView.
class NotesAdapter(private val clickListener: (Note) -> Unit) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(DiffCallback) {

    // NoteViewHolder: Holds and binds the view for a single note item.
    class NoteViewHolder(itemView: View, val clickListener: (Note) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.noteTitle) // TextView for displaying the note's title.
        val contentTextView: TextView = itemView.findViewById(R.id.noteContent) // TextView for displaying the note's content.
        var currentNote: Note? = null // The current note associated with this ViewHolder.

        // init: Sets up a click listener for the note item.
        init {
            itemView.setOnClickListener {
                currentNote?.let {
                    clickListener(it)
                }
            }
        }

        // bind: Binds a note to the ViewHolder, updating the displayed title and content.
        fun bind(note: Note) {
            currentNote = note
            titleTextView.text = note.title
            contentTextView.text = note.content
        }
    }

    // onCreateViewHolder: Inflates the note item view and returns a new ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, clickListener)
    }

    // onBindViewHolder: Binds a note to a ViewHolder based on its position in the list.
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)
    }

    // DiffCallback: Utility for calculating the difference between two lists to enable efficient updates.
    companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
        // areItemsTheSame: Checks if two notes have the same ID.
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        // areContentsTheSame: Checks if two notes are equal.
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
