package ru.serega.testNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class TaskFragment : Fragment() {

    private lateinit var adapter: NoteAdapter
    private val notes = NotesData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_task, container, false)

        val recView = view?.findViewById<RecyclerView>(R.id.rcView)
        val editView = view?.findViewById<EditText>(R.id.edit_note)
        val addView = view?.findViewById<Button>(R.id.button_add)

        adapter = NoteAdapter { note ->
            showEditDialog(note)
        }

        recView?.adapter = adapter
        adapter.data = NotesData.getNotes().toList()

        addView?.setOnClickListener {
            val text = editView?.text.toString()
            if (text.isNotEmpty()) {
                NotesData.addNote(text, iconRes = R.drawable.circle_icon)
                adapter.data = NotesData.getNotes().toList()
                editView?.setText("")
            }
        }
        return view
    }

    private fun showEditDialog(note: Note) {
        val edit = EditText(requireContext())
        edit.setText(note.text)

        AlertDialog.Builder(requireContext())
            .setTitle("Edit note")
            .setView(edit)
            .setPositiveButton("Save") { _, _ ->
                val updated = note.copy(text = edit.text.toString())
                notes.updateNote(updated)
                adapter.data = notes.getNotes().toList()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}

