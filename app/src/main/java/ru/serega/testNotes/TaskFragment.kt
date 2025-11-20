package ru.serega.testNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class TaskFragment : Fragment() {
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = NoteAdapter()

        val rVNotes = view?.findViewById<RecyclerView>(R.id.rcView)
        rVNotes?.adapter = adapter

        adapter.data = NotesData.getNotes()

        val viewEdit = view?.findViewById<EditText>(R.id.edit_note)
        viewEdit?.setOnClickListener {  }

        return inflater.inflate(R.layout.fragment_task, container, false)
    }
}