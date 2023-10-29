package evtomak.iu.edu.notes2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class NotesListFragment : Fragment() {
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)

        notesRecyclerView = view.findViewById(R.id.notesRecyclerView)
        notesAdapter = NotesAdapter()
        notesRecyclerView.adapter = notesAdapter

        // Create an instance of NoteRepository here (or get it from somewhere)
        val noteRepository = NoteRepository() // This is just an example, replace it with actual code

        // Create an instance of NoteViewModelFactory
        val factory = NoteViewModelFactory(noteRepository)

        // Use the factory to get the NoteViewModel
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
            notesAdapter.submitList(notes)
        }

        return view
    }
}
