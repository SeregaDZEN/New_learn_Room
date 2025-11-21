package ru.serega.testNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TaskFragment : Fragment() {
    private lateinit var adapter: NoteAdapter
    private var selectedNote: Note? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_task, container, false)
        val recView = view?.findViewById<RecyclerView>(R.id.rcView)
        val editView = view?.findViewById<EditText>(R.id.edit_note)
        val addView = view?.findViewById<Button>(R.id.button_add)



        adapter = NoteAdapter{ note ->
            selectedNote = note
            editView?.setText(note.text)

        }
        recView?.adapter = adapter
        recView?.layoutManager = LinearLayoutManager(requireContext())
        adapter.data = NotesData.getNotes()

        addView?.setOnClickListener {
            val text = editView?.text.toString()
            if (text.isNotBlank()) {
                if (selectedNote != null) {
                    NotesData.updateNote(selectedNote!!.copy(text = text))
                    selectedNote = null
                } else {
                    NotesData.addNote(text, R.drawable.circle_icon)
                }
                adapter.data = NotesData.getNotes()  // notifyDataSetChanged() вызывается здесь автоматически
                editView?.text?.clear()
            }
        }
        return view
    }
}