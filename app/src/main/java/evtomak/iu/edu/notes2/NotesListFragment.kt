package evtomak.iu.edu.notes2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class NotesListFragment : Fragment() {
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private val noteViewModel: NoteViewModel by viewModels { NoteViewModelFactory(NoteRepositorySingleton.getInstance()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)

        notesRecyclerView = view.findViewById(R.id.notesRecyclerView)
        notesAdapter = NotesAdapter()
        notesRecyclerView.adapter = notesAdapter
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
            notesAdapter.submitList(notes)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addNoteButton = view.findViewById<ImageButton>(R.id.addNoteButton)
        val userScreenButton = view.findViewById<ImageButton>(R.id.userScreenButton)

        addNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteScreen)
        }

        userScreenButton.setOnClickListener {
            Log.d("NotesListFragment", "User screen button clicked")
            Log.d("NotesListFragment", "Before back stack: ${findNavController().currentBackStackEntry?.destination}")
            findNavController().navigate(R.id.action_notesListFragment_to_userScreen)
            Log.d("NotesListFragment", "After back stack: ${findNavController().currentBackStackEntry?.destination}")
        }
    }
}
