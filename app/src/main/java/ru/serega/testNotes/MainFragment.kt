package ru.serega.testNotes

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment(R.layout.fragment_task), NoteClickListener {

    private lateinit var adapter: NoteAdapter
    private val notes = NotesData


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val recView = view.findViewById<RecyclerView>(R.id.rcView)
        val editView = view.findViewById<EditText>(R.id.edit_note)
        val addView = view.findViewById<Button>(R.id.button_add)

        adapter = NoteAdapter(this)


        recView?.adapter = adapter
        adapter.submitList(NotesData.currentList())



        addView?.setOnClickListener {
            val text = editView?.text.toString()
            if (text.isNotEmpty()) {
                NotesData.addNote(text, iconRes = R.drawable.circle_icon)
                adapter.submitList(NotesData.currentList())
                editView?.setText("")
            }
        }
    }

    private fun showEditDialog(note: NoteModel) {
        val edit = EditText(requireContext())
        edit.setText(note.text)

        AlertDialog.Builder(requireContext())
            .setTitle("Edit note")
            .setView(edit)
            .setPositiveButton("Save") { dialog, _ ->
                val updated = note.copy(text = edit.text.toString())
                notes.updateNote(updated)
                adapter.submitList(NotesData.currentList())

                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(edit.windowToken, 0)

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onEditClick(note: NoteModel) {
        showEditDialog(note)
    }

    override fun onStatusClick(note: NoteModel, view: View) {

        // Вибрация на клик
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)

        val message = if (note.isDone) "'не выполнена'" else "'выполнена'"

        //  Показываем Snackbar
        val snack = Snackbar.make(view, "Заметка c id: ${note.id} $message", Snackbar.LENGTH_SHORT)

        //  Достаём TextView Snackbar'а
        val snackbarText =
            snack.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

        //  Генерируем случайный цвет
        val randomColor = Color.rgb(
            (0..255).random(),
            (0..255).random(),
            (0..255).random()
        )

        //  Применяем случайный цвет текста
        snackbarText.setTextColor(randomColor)

        //  Показываем Snackbar
        snack.show()
    }

    override fun onTextClick(note: NoteModel) {
        val fragment = EditNoteFragment().apply {
            arguments = Bundle().apply {
                putInt("note", note.id)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}

