package ru.serega.testNotes

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private val note: NoteModel by lazy {
        val id = requireArguments().getInt("note")
        NotesData.currentList().first { it.id == id }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val textId = view.findViewById<TextView>(R.id.textEditFragment)
        val statusView = view.findViewById<ImageView>(R.id.img_statusEdit)
        val textNoteEdit = view.findViewById<TextView>(R.id.text_noteEdit)
        val btnUp = view.findViewById<Button>(R.id.but_update)




        textId.text = getString(R.string.noteId, note.id)
        statusView.isSelected = note.isDone
        textNoteEdit.text = note.text


        btnUp.setOnClickListener {
            val updated = note.copy(text = "${note.text} вот и обновил!!!")
            parentFragmentManager.setFragmentResult(
                "edit_result",
                Bundle().apply {
                    putParcelable("updated_note", updated)
                }
            )

            parentFragmentManager.popBackStack() // вернуться назад
        }
    }
}

